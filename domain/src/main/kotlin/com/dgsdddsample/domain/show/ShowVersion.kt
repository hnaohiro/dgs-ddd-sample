package com.dgsdddsample.domain.show

@JvmInline
value class ShowVersion(private val value: Int) {
    init {
        require(value > 0)
    }

    fun int(): Int {
        return value
    }

    fun increment(): ShowVersion {
        return ShowVersion(value + 1)
    }

    companion object {
        val init = ShowVersion(1)
    }
}
