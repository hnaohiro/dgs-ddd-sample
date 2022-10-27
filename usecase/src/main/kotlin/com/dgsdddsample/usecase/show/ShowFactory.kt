package com.dgsdddsample.usecase.show

import com.dgsdddsample.domain.shared.ULIDGenerator
import com.dgsdddsample.domain.show.*

class ShowFactory(
    private val showRepository: ShowRepository,
    private val ulidGenerator: ULIDGenerator
) {
    fun build(
        title: Title,
        releaseYear: ReleaseYear,
    ): Show {
        if (showRepository.existBy(title, releaseYear)) {
            throw IllegalArgumentException("title and releaseYear already exist")
        }

        return Show(
            id = ShowId(ulidGenerator.generate()),
            version = ShowVersion.init,
            title = title,
            releaseYear = releaseYear,
        )
    }

    fun build(
        id: ShowId,
        version: ShowVersion,
        title: Title,
        releaseYear: ReleaseYear,
    ): Show {
        val show = showRepository.findById(id) ?: throw IllegalArgumentException("not found")

        if (show.version != version) {
            throw IllegalArgumentException("invalid version")
        }

        return Show(
            id = id,
            version = version.increment(),
            title = title,
            releaseYear = releaseYear
        )
    }
}
