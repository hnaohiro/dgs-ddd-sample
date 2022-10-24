package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.*

class UpdateShowUseCase(
    private val showRepository: ShowRepository,
    private val showFactory: ShowFactory,
) {
    data class Params(val id: String, val version: Int, val title: String, val releaseYear: Int)
    data class DTO(val show: Show? = null, val error: String? = null)

    fun handle(params: Params): DTO {
        val show = showFactory.build(
            id = ShowId.from(params.id),
            version = ShowVersion(params.version),
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
