package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.domain.show.ShowRepository

class DeleteShowUseCase(
    private val showRepository: ShowRepository,
) {
    data class Params(val id: String)
    data class DTO(val error: String? = null)

    fun handle(params: Params): DTO {
        return if (showRepository.delete(ShowId.from(params.id))) {
            DTO(error = null)
        } else {
            DTO(error = "failed to save")
        }
    }
}
