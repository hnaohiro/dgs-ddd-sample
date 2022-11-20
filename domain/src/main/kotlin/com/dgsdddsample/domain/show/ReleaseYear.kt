package com.dgsdddsample.domain.show

@JvmInline
value class ReleaseYear(private val value: Int) {
    init {
        require(value > 1900) { "value should be greater than 1900" }
    }

    fun int(): Int = value
}
