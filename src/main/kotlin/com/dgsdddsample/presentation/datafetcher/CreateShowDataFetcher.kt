package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.infrastructure.KULIDGenerator
import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.presentation.generated.types.CreateShowInput
import com.dgsdddsample.presentation.generated.types.CreateShowPayload
import com.dgsdddsample.usecase.show.CreateShowUseCase
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import org.jetbrains.exposed.sql.transactions.transaction

@DgsComponent
class CreateShowDataFetcher {
    private val showRepository = ShowExposedRepository()
    private val ulidGenerator = KULIDGenerator()
    private val createShowUseCase = CreateShowUseCase(showRepository, ulidGenerator)
    private val showAdapter = ShowAdapter()

    @DgsMutation
    fun createShow(@InputArgument input: CreateShowInput): CreateShowPayload {
        val params = CreateShowUseCase.Params(
            title = input.title,
            releaseYear = input.releaseYear,
        )
        val dto = transaction {
            createShowUseCase.handle(params)
        }
        return CreateShowPayload(
            show = dto.show?.let { showAdapter.adapt(it) },
            error = dto.error,
        )
    }
}
