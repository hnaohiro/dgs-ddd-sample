package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.show.factory.ShowFactory
import com.dgsdddsample.usecase.transaction.NopTransactionManager
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CreateShowUsecaseTest : BehaviorSpec() {
    private val showRepository = mockk<ShowRepository>()
    private val showFactory = mockk<ShowFactory>()
    private val createShowUseCase = CreateShowUseCase(NopTransactionManager(), showRepository, showFactory)

    init {
        Given("handle") {
            When("normal scenario") {
                val show = Show(
                    id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                    version = ShowVersion.init,
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { showFactory.build(any(), any()) } returns show
                every { showRepository.save(any()) } returns true

                Then("returns valid show") {
                    CreateShowUseCase
                        .Params("test", 2022)
                        .let { createShowUseCase.handle(it) }
                        .shouldBe(CreateShowUseCase.DTO(show))
                }
            }

            When("showFactory.build throws IllegalArgumentException") {
                every { showFactory.build(any(), any()) } throws IllegalArgumentException()

                Then("throws IllegalArgumentException") {
                    shouldThrow<IllegalArgumentException> {
                        createShowUseCase.handle(CreateShowUseCase.Params("test", 2022))
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
                every { showFactory.build(any(), any()) } returns show
                every { showRepository.save(any()) } returns false

                Then("returns DTO(error)") {
                    CreateShowUseCase
                        .Params("test", 2022)
                        .let { createShowUseCase.handle(it) }
                        .shouldBe(CreateShowUseCase.DTO(error = "failed to save"))
                }
            }
        }
    }
}
