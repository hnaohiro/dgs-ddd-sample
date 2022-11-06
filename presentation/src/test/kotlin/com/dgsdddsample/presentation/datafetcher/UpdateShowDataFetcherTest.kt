package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.presentation.generated.types.UpdateShowInput
import com.dgsdddsample.presentation.generated.types.UpdateShowPayload
import com.dgsdddsample.usecase.show.UpdateShowUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import com.dgsdddsample.presentation.generated.types.Show as GraphQLShow

class UpdateShowDataFetcherTest : BehaviorSpec() {
    private val updateShowUseCase = mockk<UpdateShowUseCase>()
    private val updateShowDataFetcher = UpdateShowDataFetcher()

    init {
        beforeSpec {
            startKoin {
                modules(module { single { updateShowUseCase } })
            }
        }

        afterSpec {
            stopKoin()
        }

        Given("show") {
            When("updateShowUseCase.handle returns DTO(show)") {
                every { updateShowUseCase.handle(any()) } returns UpdateShowUseCase.DTO(
                    Show(
                        id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                        version = ShowVersion(2),
                        title = Title("test"),
                        releaseYear = ReleaseYear(2022),
                    )
                )

                Then("returns Payload(show)") {
                    val input = UpdateShowInput(
                        id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                        version = 1,
                        title = "test",
                        releaseYear = 2022,
                    )
                    updateShowDataFetcher.updateShow(input) shouldBe UpdateShowPayload(
                        show = GraphQLShow(
                            id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                            version = 2,
                            title = "test",
                            releaseYear = 2022,
                        ),
                        error = null,
                    )
                }
            }

            When("updateShowUseCase.handle returns DTO(error)") {
                every { updateShowUseCase.handle(any()) } returns UpdateShowUseCase.DTO(error = "failed to save")

                Then("returns Payload(error)") {
                    val input = UpdateShowInput(
                        id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                        version = 1,
                        title = "test",
                        releaseYear = 2022,
                    )
                    updateShowDataFetcher.updateShow(input) shouldBe UpdateShowPayload(
                        show = null,
                        error = "failed to save",
                    )
                }
            }
        }
    }
}
