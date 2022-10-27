package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.adapter.egress.show.adapt
import com.dgsdddsample.presentation.adapter.ingress.querycondition.int.adapt
import com.dgsdddsample.presentation.adapter.ingress.querycondition.string.adapt
import com.dgsdddsample.presentation.generated.types.IntCondition
import com.dgsdddsample.presentation.generated.types.Show
import com.dgsdddsample.presentation.generated.types.StringCondition
import com.dgsdddsample.usecase.show.GetShowsUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class ShowsDataFetcher : KoinComponent {
    private val getShowsUseCase: GetShowsUseCase by inject()

    @DgsQuery
    fun shows(
        @InputArgument title: StringCondition?,
        @InputArgument releaseYear: IntCondition?,
    ): List<Show> {
        return getShowsUseCase.handle(
            GetShowsUseCase.Params(
                title = title?.adapt(),
                releaseYear = releaseYear?.adapt(),
            )
        ).let { dto ->
            dto.shows.map { it.adapt() }
        }
    }
}
