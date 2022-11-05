package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.usecase.queryservice.condition.IntQueryCondition
import com.dgsdddsample.usecase.queryservice.condition.StringCondition
import com.dgsdddsample.usecase.show.service.ShowsQueryService
import com.dgsdddsample.usecase.transaction.TransactionManager

class GetShowsUseCase(
    private val transactionManager: TransactionManager,
    private val showsQueryService: ShowsQueryService,
) {
    data class Params(val title: StringCondition? = null, val releaseYear: IntQueryCondition? = null)
    data class DTO(val shows: List<Show>)

    fun handle(params: Params): DTO {
        return transactionManager.transaction {
            DTO(showsQueryService.search(params.title, params.releaseYear))
        }
    }
}
