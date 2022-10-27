package com.dgsdddsample.domain.show

import com.dgsdddsample.domain.shared.ULID

@JvmInline
value class ShowId(private val ulid: ULID) {
    fun string() = ulid.string()

    companion object {
        fun from(value: String) = ShowId(ULID(value))
    }
}
