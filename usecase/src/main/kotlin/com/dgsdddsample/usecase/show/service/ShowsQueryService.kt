package com.dgsdddsample.usecase.show.service

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.usecase.queryservice.condition.IntQueryCondition
import com.dgsdddsample.usecase.queryservice.condition.StringQueryCondition

interface ShowsQueryService {
    fun search(
        title: StringQueryCondition?,
        releaseYear: IntQueryCondition?,
    ): List<Show>
}

