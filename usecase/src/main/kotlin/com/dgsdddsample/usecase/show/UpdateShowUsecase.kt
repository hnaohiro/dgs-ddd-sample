package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.usecase.transaction.TransactionManager

class UpdateShowUseCase(
    private val transactionManager: TransactionManager,
    private val showRepository: ShowRepository,
    private val showFactory: ShowFactory,
) {
    data class Params(val id: String, val version: Int, val title: String, val releaseYear: Int)
    data class DTO(val show: Show? = null, val error: String? = null)

    fun handle(params: Params): DTO {
        return transactionManager.transaction {
            val show = showFactory.build(
                id = ShowId.from(params.id),
                version = ShowVersion(params.version),
                title = Title(params.title),
                releaseYear = ReleaseYear(params.releaseYear),
            )

            if (showRepository.save(show)) {
                DTO(show)
            } else {
                DTO(error = "failed to save")
            }
        }
    }
}
