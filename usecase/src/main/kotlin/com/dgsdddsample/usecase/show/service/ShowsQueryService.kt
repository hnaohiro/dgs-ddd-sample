package com.dgsdddsample.usecase.show.service

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.usecase.queryservice.condition.IntQueryCondition
import com.dgsdddsample.usecase.queryservice.condition.StringCondition

interface ShowsQueryService {
    fun search(
        title: StringCondition?,
        releaseYear: IntQueryCondition?,
    ): List<Show>
}

