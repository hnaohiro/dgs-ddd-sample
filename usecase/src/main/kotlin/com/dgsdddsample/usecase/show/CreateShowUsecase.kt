package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.usecase.show.factory.ShowFactory
import com.dgsdddsample.usecase.transaction.TransactionManager

class CreateShowUseCase(
    private val transactionManager: TransactionManager,
    private val showRepository: ShowRepository,
    private val showFactory: ShowFactory,
) {
    data class Params(val title: String, val releaseYear: Int)
    data class DTO(val show: Show? = null, val error: String? = null)

    fun handle(params: Params): DTO {
        return transactionManager.transaction {
            val show = showFactory.build(
                title = params.title,
                releaseYear = params.releaseYear,
            )

            if (showRepository.save(show)) {
                DTO(show)
            } else {
                DTO(error = "failed to save")
            }
        }
    }
}
