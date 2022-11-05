package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.show.factory.ShowFactory
import com.dgsdddsample.usecase.transaction.NopTransactionManager
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class UpdateShowUsecaseTest : BehaviorSpec() {
    private val showRepository = mockk<ShowRepository>()
    private val showFactory = mockk<ShowFactory>()
    private val updateShowUseCase = UpdateShowUseCase(NopTransactionManager(), showRepository, showFactory)

    init {
        Given("handle") {
            val showId = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")

            When("normal scenario") {
                val show = Show(
                    id = showId,
                    version = ShowVersion.init.increment(),
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { showFactory.buildWithIdAndVersion(any(), any(), any(), any()) } returns show
                every { showRepository.save(any()) } returns true

                Then("returns valid show") {
                    UpdateShowUseCase
                        .Params(showId.string(), 1, "test", 2022)
                        .let { updateShowUseCase.handle(it) }
                        .shouldBe(UpdateShowUseCase.DTO(show))
                }
            }

            When("showFactory.build throws IllegalArgumentException") {
                every {
                    showFactory.buildWithIdAndVersion(any(), any(), any(), any())
                } throws IllegalArgumentException()

                Then("throws IllegalArgumentException") {
                    shouldThrow<IllegalArgumentException> {
                        updateShowUseCase.handle(UpdateShowUseCase.Params(showId.string(), 1, "test", 2022))
                    }
                }
            }

            When("showRepository.save returns false") {
                val show = Show(
                    id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                    version = ShowVersion.init,
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { showFactory.buildWithIdAndVersion(any(), any(), any(), any()) } returns show
                every { showRepository.save(any()) } returns false

                Then("returns DTO(error)") {
                    UpdateShowUseCase
                        .Params(showId.string(), 1, "test", 2022)
                        .let { updateShowUseCase.handle(it) }
                        .shouldBe(UpdateShowUseCase.DTO(error = "failed to save"))
                }
            }
        }
    }
}
