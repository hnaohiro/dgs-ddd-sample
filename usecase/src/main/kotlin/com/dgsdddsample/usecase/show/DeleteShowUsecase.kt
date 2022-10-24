package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.usecase.transaction.TransactionManager

class DeleteShowUseCase(
    private val transactionManager: TransactionManager,
    private val showRepository: ShowRepository,
) {
    data class Params(val id: String)
    data class DTO(val error: String? = null)

    fun handle(params: Params): DTO {
        return transactionManager.transaction {
            if (showRepository.delete(ShowId.from(params.id))) {
                DTO(error = null)
            } else {
                DTO(error = "failed to save")
            }
        }
    }
}
