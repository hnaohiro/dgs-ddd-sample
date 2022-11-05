package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.presentation.adapter.egress.show.adapt
import com.dgsdddsample.presentation.generated.types.Show
import com.dgsdddsample.usecase.show.GetShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class ShowDataFetcher : KoinComponent {
    private val getShowUseCase: GetShowUseCase by inject()

    @DgsQuery
    fun show(@InputArgument id: String): Show? {
        return GetShowUseCase
            .Params(
                id = ShowId.from(id),
            )
            .let { getShowUseCase.handle(it) }
            .let { dto ->
                dto.show?.adapt()
            }
    }
}
