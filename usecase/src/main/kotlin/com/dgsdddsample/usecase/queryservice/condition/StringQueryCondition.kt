package com.dgsdddsample.usecase.queryservice.condition

data class StringCondition(
    val value: String,
    val matchType: MatchType,
)

enum class MatchType {
    FULL,
    PARTIAL,
}
