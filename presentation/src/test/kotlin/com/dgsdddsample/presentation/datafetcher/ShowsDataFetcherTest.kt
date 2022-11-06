package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.show.GetShowsUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.dsl.module
import com.dgsdddsample.presentation.generated.types.Show as GraphQLShow

class ShowsDataFetcherTest : BehaviorSpec() {
    private val getShowsUseCase = mockk<GetShowsUseCase>()
    private val showsDataFetcher = ShowsDataFetcher()

    init {
        startKoin {
            modules(module { single { getShowsUseCase } })
        }

        Given("shows") {
            When("getShowsUseCase.handle returns non empty list") {
                val show = Show(
                    id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                    version = ShowVersion.init,
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { getShowsUseCase.handle(any()) } returns GetShowsUseCase.DTO(listOf(show))

                Then("returns list(show)") {
                    showsDataFetcher.shows(title = null, releaseYear = null) shouldBe listOf(
                        GraphQLShow(
                            id = "01GGXJJSBWQZYP8FZAGEB4YWEW",
                            version = 1,
                            title = "test",
                            releaseYear = 2022,
                        )
                    )
                }
            }

            When("getShowsUseCase.handle returns DTO(empty list)") {
                every { getShowsUseCase.handle(any()) } returns GetShowsUseCase.DTO(emptyList())

                Then("returns empty list") {
                    showsDataFetcher.shows(title = null, releaseYear = null) shouldBe emptyList()
                }
            }
        }
    }
}
