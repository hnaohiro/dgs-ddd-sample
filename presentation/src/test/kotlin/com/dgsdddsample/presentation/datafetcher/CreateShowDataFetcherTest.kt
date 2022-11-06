package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.presentation.generated.types.CreateShowInput
import com.dgsdddsample.presentation.generated.types.CreateShowPayload
import com.dgsdddsample.usecase.show.CreateShowUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import com.dgsdddsample.presentation.generated.types.Show as GraphQLShow

class CreateShowDataFetcherTest : BehaviorSpec() {
    private val createShowUseCase = mockk<CreateShowUseCase>()
    private val createShowDataFetcher = CreateShowDataFetcher()

    init {
        beforeSpec {
            startKoin {
                modules(module { single { createShowUseCase } })
            }
        }

        afterSpec {
            stopKoin()
        }

        Given("createShow") {
            When("createShowUseCase.handle returns DTO(show)") {
                every { createShowUseCase.handle(any()) } returns CreateShowUseCase.DTO(
                    Show(
                        id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                        version = ShowVersion.init,
                        title = Title("test"),
                        releaseYear = ReleaseYear(2022),
                    )
                )

                Then("returns Payload(show)") {
                    val input = CreateShowInput(
                        title = "test",
                        releaseYear = 2022,
                    )
                    createShowDataFetcher.createShow(input) shouldBe CreateShowPayload(
                        show = GraphQLShow(
                            id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                            version = 1,
                            title = "test",
                            releaseYear = 2022,
                        ),
                        error = null,
                    )
                }
            }

            When("createShowUseCase.handle returns DTO(error)") {
                every { createShowUseCase.handle(any()) } returns CreateShowUseCase.DTO(error = "failed to save")

                Then("returns Payload(error)") {
                    val input = CreateShowInput(
                        title = "test",
                        releaseYear = 2022,
                    )
                    createShowDataFetcher.createShow(input) shouldBe CreateShowPayload(
                        show = null,
                        error = "failed to save",
                    )
                }
            }
        }
    }
}
