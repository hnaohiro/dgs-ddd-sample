package com.dgsdddsample.usecase.queryservice.condition

data class StringQueryCondition(
    val value: String,
    val matchType: MatchType,
)

enum class MatchType {
    FULL,
    PARTIAL,
}
