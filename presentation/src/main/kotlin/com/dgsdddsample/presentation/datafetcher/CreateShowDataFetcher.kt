package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.presentation.generated.types.CreateShowInput
import com.dgsdddsample.presentation.generated.types.CreateShowPayload
import com.dgsdddsample.usecase.show.CreateShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class CreateShowDataFetcher : KoinComponent {
    private val createShowUseCase: CreateShowUseCase by inject()
    private val showAdapter: ShowAdapter by inject()

    @DgsMutation
    fun createShow(@InputArgument input: CreateShowInput): CreateShowPayload {
        return createShowUseCase.handle(
            CreateShowUseCase.Params(
                title = input.title,
                releaseYear = input.releaseYear,
            )
        ).let { dto ->
            CreateShowPayload(
                show = dto.show?.let { showAdapter.adapt(it) },
                error = dto.error,
            )
        }
    }
}
