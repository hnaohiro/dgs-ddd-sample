package com.dgsdddsample.presentation.datafetcher

import com.dgsdddsample.presentation.adapter.egress.show.adapt
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

    @DgsMutation
    fun createShow(@InputArgument input: CreateShowInput): CreateShowPayload {
        return CreateShowUseCase
            .Params(
                title = input.title,
                releaseYear = input.releaseYear,
            )
            .let { createShowUseCase.handle(it) }
            .let { dto ->
                CreateShowPayload(
                    show = dto.show?.adapt(),
                    error = dto.error,
                )
            }
    }
}
