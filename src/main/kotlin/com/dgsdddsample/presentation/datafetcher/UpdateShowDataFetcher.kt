package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.infrastructure.KULIDGenerator
import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.presentation.generated.types.UpdateShowInput
import com.dgsdddsample.presentation.generated.types.UpdateShowPayload
import com.dgsdddsample.usecase.show.UpdateShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.jetbrains.exposed.sql.transactions.transaction

@DgsComponent
class UpdateShowDataFetcher {
    private val showRepository = ShowExposedRepository()
    private val ulidGenerator = KULIDGenerator()
    private val updateShowUseCase = UpdateShowUseCase(showRepository, ulidGenerator)
    private val showAdapter = ShowAdapter()

    @DgsMutation
    fun updateShow(@InputArgument input: UpdateShowInput): UpdateShowPayload {
        val params = UpdateShowUseCase.Params(
            id = input.id,
            version = input.version,
            title = input.title,
            releaseYear = input.releaseYear,
        )
        val dto = transaction {
            updateShowUseCase.handle(params)
        }
        return UpdateShowPayload(
            show = dto.show?.let { showAdapter.adapt(it) },
            error = dto.error,
        )
    }
}
