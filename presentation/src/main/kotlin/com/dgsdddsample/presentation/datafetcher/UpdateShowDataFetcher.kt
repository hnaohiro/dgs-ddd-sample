package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.presentation.generated.types.UpdateShowInput
import com.dgsdddsample.presentation.generated.types.UpdateShowPayload
import com.dgsdddsample.usecase.show.UpdateShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class UpdateShowDataFetcher : KoinComponent {
    private val updateShowUseCase: UpdateShowUseCase by inject()
    private val showAdapter: ShowAdapter by inject()

    @DgsMutation
    fun updateShow(@InputArgument input: UpdateShowInput): UpdateShowPayload {
        return updateShowUseCase.handle(
            UpdateShowUseCase.Params(
                id = input.id,
                version = input.version,
                title = input.title,
                releaseYear = input.releaseYear,
            )
        ).let { dto ->
            UpdateShowPayload(
                show = dto.show?.let { showAdapter.adapt(it) },
                error = dto.error,
            )
        }
    }
}
