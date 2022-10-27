package com.dgsdddsample.domain.show

data class Title(private val value: String) {
    init {
        require(value.isNotEmpty())
    }

    fun string() = value
}
