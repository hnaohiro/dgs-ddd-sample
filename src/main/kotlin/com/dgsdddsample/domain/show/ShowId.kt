package com.dgsdddsample.domain.show

import com.dgsdddsample.domain.shared.ULID
import com.dgsdddsample.domain.shared.ULIDGenerator

@JvmInline
value class ShowId(private val ulid: ULID) {
    fun string(): String {
        return ulid.string()
    }

    companion object {
        fun create(ulidGenerator: ULIDGenerator): ShowId {
            return ShowId(ulidGenerator.generate())
        }

        fun from(value: String): ShowId {
            return ShowId(ULID(value))
        }
    }
}
