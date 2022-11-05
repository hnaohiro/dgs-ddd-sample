package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.show.service.ShowsQueryService
import com.dgsdddsample.usecase.transaction.NopTransactionManager
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class GetShowsUsecaseTest : BehaviorSpec() {
    private val showsQueryService = mockk<ShowsQueryService>()
    private val getShowsUseCase = GetShowsUseCase(NopTransactionManager(), showsQueryService)

    init {
        Given("handle") {
            When("showsQueryService.search returns non empty list") {
                val show = Show(
                    id = ShowId.from("01GGXJJSBWQZYP8FZAGEB4YWEW"),
                    version = ShowVersion.init,
                    title = Title("test"),
                    releaseYear = ReleaseYear(2022),
                )
                every { showsQueryService.search(any(), any()) } returns listOf(show)

                Then("returns DTO(show)") {
                    getShowsUseCase.handle(GetShowsUseCase.Params()) shouldBe GetShowsUseCase.DTO(listOf(show))
                }
            }

            When("showsQueryService.search returns empty list") {
                every { showsQueryService.search(any(), any()) } returns emptyList()

                Then("returns DTO(show is null)") {
                    getShowsUseCase.handle(GetShowsUseCase.Params()) shouldBe GetShowsUseCase.DTO(emptyList())
                }
            }
        }
    }
}
