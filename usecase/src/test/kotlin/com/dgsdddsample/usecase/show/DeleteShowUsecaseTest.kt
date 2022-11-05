package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.usecase.transaction.NopTransactionManager
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class DeleteShowUsecaseTest : BehaviorSpec() {
    private val showRepository = mockk<ShowRepository>()
    private val deleteShowUseCase = DeleteShowUseCase(NopTransactionManager(), showRepository)

    init {
        Given("handle") {
            val showId = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")

            When("normal scenario") {
                every { showRepository.delete(showId) } returns true

                Then("returns DTO(error = null)") {
                    DeleteShowUseCase
                        .Params(showId.string())
                        .let { deleteShowUseCase.handle(it) }
                        .shouldBe(DeleteShowUseCase.DTO(error = null))
                }
            }

            When("showRepository.delete returns false") {
                every { showRepository.delete(showId) } returns false

                Then("returns DTO(error)") {
                    DeleteShowUseCase
                        .Params(showId.string())
                        .let { deleteShowUseCase.handle(it) }
                        .shouldBe(DeleteShowUseCase.DTO(error = "failed to delete"))
                }
            }
        }
    }
}
