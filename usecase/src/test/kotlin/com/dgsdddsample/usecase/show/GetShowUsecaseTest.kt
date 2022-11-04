package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.transaction.NopTransactionManager
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetShowUsecaseTest : BehaviorSpec() {
    private val showRepository = mockk<ShowRepository>()
    private val getShowUseCase = GetShowUseCase(NopTransactionManager(), showRepository)

    init {
        Given("handle") {
            When("showRepository.findById returns Show") {
                val showId = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")
                val show = Show(
                    id = showId,
                    version = ShowVersion.init,
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { showRepository.findById(showId) } returns show

                Then("returns DTO(show)") {
                    getShowUseCase.handle(GetShowUseCase.Params(showId)) shouldBe GetShowUseCase.DTO(show)
                }
            }

            When("showRepository.findById returns null") {
                val showId = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")
                every { showRepository.findById(showId) } returns null

                Then("returns DTO(show is null)") {
                    getShowUseCase.handle(GetShowUseCase.Params(showId)) shouldBe GetShowUseCase.DTO(null)
                }
            }
        }
    }
}
