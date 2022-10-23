package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.presentation.generated.types.DeleteShowPayload
import com.dgsdddsample.presentation.generated.types.UpdateShowInput
import com.dgsdddsample.usecase.show.DeleteShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.jetbrains.exposed.sql.transactions.transaction

@DgsComponent
class DeleteShowDataFetcher {
    private val showRepository = ShowExposedRepository()
    private val deleteShowUseCase = DeleteShowUseCase(showRepository)

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
