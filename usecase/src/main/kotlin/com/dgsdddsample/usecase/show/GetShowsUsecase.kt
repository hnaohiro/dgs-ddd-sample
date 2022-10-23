package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.domain.show.ShowRepository

class GetShowsUseCase(
    private val showRepository: ShowRepository,
) {
    data class Params(val titleFilter: String? = null)
    data class DTO(val shows: List<Show>)

    fun handle(params: Params): DTO {
        val shows = showRepository.findByTitle(params.titleFilter)
        return DTO(shows)
    }
}
