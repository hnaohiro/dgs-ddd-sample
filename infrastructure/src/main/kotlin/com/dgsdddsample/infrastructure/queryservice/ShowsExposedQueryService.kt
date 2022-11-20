package com.dgsdddsample.infrastructure.queryservice

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.infrastructure.adapter.reconstructShow
import com.dgsdddsample.infrastructure.table.ShowTable
import com.dgsdddsample.usecase.queryservice.condition.CompareType
import com.dgsdddsample.usecase.queryservice.condition.IntQueryCondition
import com.dgsdddsample.usecase.queryservice.condition.MatchType
import com.dgsdddsample.usecase.queryservice.condition.StringQueryCondition
import com.dgsdddsample.usecase.show.service.ShowsQueryService
import org.jetbrains.exposed.sql.LikePattern
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select

class ShowsExposedQueryService : ShowsQueryService {
    override fun search(title: StringQueryCondition?, releaseYear: IntQueryCondition?): List<Show> {
        return listOfNotNull(
            title?.let {
                when (it.matchType) {
                    MatchType.FULL -> Op.build { ShowTable.title eq it.value }
                    MatchType.PARTIAL -> Op.build { ShowTable.title like LikePattern("%${it.value}%") }
                }
            },
            releaseYear?.let {
                when (it.compareType) {
                    CompareType.EQUAL -> Op.build { ShowTable.releaseYear eq it.value }
                    CompareType.LESS_THAN -> Op.build { ShowTable.releaseYear less it.value }
                    CompareType.GREATER_THAN -> Op.build { ShowTable.releaseYear greater it.value }
                }
            }
        )
            .fold(Op.TRUE as Op<Boolean>) { acc, op -> acc and op }
            .let { ShowTable.select(it) }
            .map { it.reconstructShow() }
    }
}
