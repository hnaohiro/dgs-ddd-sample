package com.dgsdddsample.domain.shared

@JvmInline
value class ULID(private val value: String) {
    init {
        require(value.length == 26)
    }

    fun string() = value
}
