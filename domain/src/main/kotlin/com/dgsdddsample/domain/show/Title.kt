package com.dgsdddsample.domain.show

data class Title(private val value: String) {
    init {
        require(value.isNotEmpty()) { "value should not be empty" }
        require(value.length <= 50) { "length should be less than 51" }
    }

    fun string() = value
}
