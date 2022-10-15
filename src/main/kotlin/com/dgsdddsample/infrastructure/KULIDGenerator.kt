package com.dgsdddsample.infrastructure

import com.dgsdddsample.domain.shared.ULID
import com.dgsdddsample.domain.shared.ULIDGenerator
import com.github.guepardoapps.kulid.ULID as KULID

class KULIDGenerator : ULIDGenerator {
    override fun generate(): ULID {
        val value = KULID.random()
        return ULID(value)
    }
}
