package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.generated.types.DeleteShowPayload
import com.dgsdddsample.presentation.generated.types.UpdateShowInput
import com.dgsdddsample.usecase.show.DeleteShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@DgsComponent
class DeleteShowDataFetcher : KoinComponent {
    private val deleteShowUseCase: DeleteShowUseCase by inject()

    @DgsMutation
    fun deleteShow(@InputArgument input: UpdateShowInput): DeleteShowPayload {
        val params = DeleteShowUseCase.Params(
            id = input.id,
        )
        val dto = transaction {
            deleteShowUseCase.handle(params)
        }
        return DeleteShowPayload(
            error = dto.error,
        )
    }
}
