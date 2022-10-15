package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.domain.show.ShowRepository

class GetShowUseCase(
    private val showRepository: ShowRepository,
) {
    data class Params(val id: ShowId)
    data class DTO(val show: Show?)

    fun handle(params: Params): DTO {
        val show = showRepository.findById(params.id)
        return DTO(show)
    }
}
