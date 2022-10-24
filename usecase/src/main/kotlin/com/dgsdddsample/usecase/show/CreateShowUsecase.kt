package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.ReleaseYear
import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.domain.show.Title

class CreateShowUseCase(
    private val showRepository: ShowRepository,
    private val showFactory: ShowFactory,
) {
    data class Params(val title: String, val releaseYear: Int)
    data class DTO(val show: Show? = null, val error: String? = null)

    fun handle(params: Params): DTO {
        val show = showFactory.build(
            title = Title(params.title),
            releaseYear = ReleaseYear(params.releaseYear),
        )

        return if (showRepository.save(show)) {
            DTO(show)
        } else {
            DTO(error = "failed to save")
        }
    }
}
