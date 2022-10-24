package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.presentation.generated.types.Show
import com.dgsdddsample.usecase.show.GetShowsUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class ShowsDataFetcher : KoinComponent {
    private val getShowsUseCase: GetShowsUseCase by inject()
    private val showAdapter: ShowAdapter by inject()

    @DgsQuery
    fun shows(@InputArgument titleFilter: String?): List<Show> {
        val params = GetShowsUseCase.Params(
            titleFilter = titleFilter,
        )
        val dto = transaction {
            getShowsUseCase.handle(params)
        }
        return dto.shows.map { showAdapter.adapt(it) }
    }
}
