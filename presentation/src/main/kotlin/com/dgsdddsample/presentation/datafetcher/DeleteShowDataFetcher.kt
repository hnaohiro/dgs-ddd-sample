package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.generated.types.DeleteShowPayload
import com.dgsdddsample.presentation.generated.types.UpdateShowInput
import com.dgsdddsample.usecase.show.DeleteShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class DeleteShowDataFetcher : KoinComponent {
    private val deleteShowUseCase: DeleteShowUseCase by inject()

    @DgsMutation
    fun deleteShow(@InputArgument input: UpdateShowInput): DeleteShowPayload {
        return DeleteShowUseCase
            .Params(
                id = input.id,
            )
            .let { deleteShowUseCase.handle(it) }
            .let { dto ->
                DeleteShowPayload(
                    error = dto.error,
                )
            }
    }
}
