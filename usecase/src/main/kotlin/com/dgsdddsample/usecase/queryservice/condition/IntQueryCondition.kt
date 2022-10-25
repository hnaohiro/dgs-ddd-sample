package com.dgsdddsample.usecase.queryservice.condition

data class IntQueryCondition(
    val value: Int,
    val compareType: CompareType,
)

enum class CompareType {
    EQUAL,
    LESS_THAN,
    GREATER_THAN,
}
