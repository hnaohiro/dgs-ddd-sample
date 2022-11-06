package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.show.GetShowUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.dgsdddsample.presentation.generated.types.Show as GraphQLShow

class ShowDataFetcherTest : BehaviorSpec() {
    private val getShowUseCase = mockk<GetShowUseCase>()
    private val showDataFetcher = ShowDataFetcher()

    init {
        startKoin {
            modules(module { single { getShowUseCase } })
        }

        Given("show") {
            When("getShowUseCase.handle returns DTO(show)") {
                val id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")
                val show = Show(
                    id = id,
                    version = ShowVersion.init,
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { getShowUseCase.handle(any()) } returns GetShowUseCase.DTO(show)

                Then("returns list(show)") {
                    showDataFetcher.show(id = id.string()) shouldBe GraphQLShow(
                        id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                        version = 1,
                        title = "test",
                        releaseYear = 2022,
                    )
                }
            }

            When("getShowUseCase.handle returns null") {
                val id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW")
                every {
                    getShowUseCase.handle(GetShowUseCase.Params(id))
                } returns GetShowUseCase.DTO(null)

                Then("returns empty list") {
                    showDataFetcher.show(id.string()) shouldBe null
                }
            }
        }
    }
}
