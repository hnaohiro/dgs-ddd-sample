package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.generated.types.DeleteShowInput
import com.dgsdddsample.presentation.generated.types.DeleteShowPayload
import com.dgsdddsample.usecase.show.DeleteShowUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class DeleteShowDataFetcherTest : BehaviorSpec() {
    private val deleteShowUseCase = mockk<DeleteShowUseCase>()
    private val deleteShowDataFetcher = DeleteShowDataFetcher()

    init {
        beforeSpec {
            startKoin {
                modules(module { single { deleteShowUseCase } })
            }
        }

        afterSpec {
            stopKoin()
        }

        Given("deleteShow") {
            When("deleteShowUseCase.handle returns DTO(error=null)") {
                every { deleteShowUseCase.handle(any()) } returns DeleteShowUseCase.DTO(error = null)

                Then("returns Payload(show)") {
                    val input = DeleteShowInput(
                        id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                    )
                    deleteShowDataFetcher.deleteShow(input) shouldBe DeleteShowPayload(error = null)
                }
            }

            When("deleteShowUseCase.handle returns DTO(error)") {
                every { deleteShowUseCase.handle(any()) } returns DeleteShowUseCase.DTO(error = "failed to delete")

                Then("returns Payload(error)") {
                    val input = DeleteShowInput(
                        id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                    )
                    deleteShowDataFetcher.deleteShow(input) shouldBe DeleteShowPayload(error = "failed to delete")
                }
            }
        }
    }
}
