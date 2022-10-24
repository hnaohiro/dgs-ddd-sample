package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.domain.show.ShowId
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.usecase.transaction.TransactionManager

class GetShowUseCase(
    private val transactionManager: TransactionManager,
    private val showRepository: ShowRepository,
) {
    data class Params(val id: ShowId)
    data class DTO(val show: Show?)

    fun handle(params: Params): DTO {
        return transactionManager.transaction {
            DTO(showRepository.findById(params.id))
        }
    }
}
