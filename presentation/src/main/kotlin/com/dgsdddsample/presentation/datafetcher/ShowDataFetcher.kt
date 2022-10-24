package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.presentation.generated.types.Show
import com.dgsdddsample.usecase.show.GetShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class ShowDataFetcher : KoinComponent {
    private val getShowUseCase: GetShowUseCase by inject()
    private val showAdapter: ShowAdapter by inject()

    @DgsQuery
    fun show(@InputArgument id: String): Show? {
        val params = GetShowUseCase.Params(
            id = ShowId.from(id),
        )
        val dto = transaction {
            getShowUseCase.handle(params)
        }
        return dto.show?.let { showAdapter.adapt(it) }
    }
}
