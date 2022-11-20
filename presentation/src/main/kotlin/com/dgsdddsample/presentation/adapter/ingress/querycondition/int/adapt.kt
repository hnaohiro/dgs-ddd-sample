package com.dgsdddsample.presentation.adapter.ingress.querycondition.int

import com.dgsdddsample.presentation.generated.types.CompareType
import com.dgsdddsample.presentation.generated.types.IntCondition
import com.dgsdddsample.usecase.queryservice.condition.IntQueryCondition

fun IntCondition.adapt(): IntQueryCondition {
    return IntQueryCondition(
        value = value,
        compareType = when (compareType) {
            CompareType.EQUAL -> com.dgsdddsample.usecase.queryservice.condition.CompareType.EQUAL
            CompareType.LESS_THAN -> com.dgsdddsample.usecase.queryservice.condition.CompareType.LESS_THAN
            CompareType.GREATER_THAN -> com.dgsdddsample.usecase.queryservice.condition.CompareType.GREATER_THAN
        }
    )
}
