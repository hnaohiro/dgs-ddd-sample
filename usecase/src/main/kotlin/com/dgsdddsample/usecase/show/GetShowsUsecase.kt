package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.usecase.transaction.TransactionManager

class GetShowsUseCase(
    private val transactionManager: TransactionManager,
    private val showRepository: ShowRepository,
) {
    data class Params(val titleFilter: String? = null)
    data class DTO(val shows: List<Show>)

    fun handle(params: Params): DTO {
        return transactionManager.transaction {
            DTO(showRepository.findByTitle(params.titleFilter))
        }
    }
}
