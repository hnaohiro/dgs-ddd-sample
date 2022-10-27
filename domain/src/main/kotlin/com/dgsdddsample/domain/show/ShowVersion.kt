package com.dgsdddsample.domain.show

@JvmInline
value class ShowVersion(private val value: Int) {
    fun int() = value

    fun increment() = ShowVersion(value + 1)

    companion object {
        val init = ShowVersion(1)
    }
}
