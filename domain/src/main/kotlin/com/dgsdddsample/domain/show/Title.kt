package com.dgsdddsample.domain.show

data class Title(private val value: String) {
    init {
        require(value.isNotEmpty())
        require(value.length <= 50)
    }

    fun string() = value
}
