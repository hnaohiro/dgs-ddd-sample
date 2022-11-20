package com.dgsdddsample.presentation.adapter.ingress.querycondition.string

import com.dgsdddsample.presentation.generated.types.MatchType
import com.dgsdddsample.presentation.generated.types.StringCondition
import com.dgsdddsample.usecase.queryservice.condition.StringQueryCondition

fun StringCondition.adapt(): StringQueryCondition {
    return StringQueryCondition(
        value = value,
        matchType = when (matchType) {
            MatchType.FULL -> com.dgsdddsample.usecase.queryservice.condition.MatchType.FULL
            MatchType.PARTIAL -> com.dgsdddsample.usecase.queryservice.condition.MatchType.PARTIAL
        }
    )
}
