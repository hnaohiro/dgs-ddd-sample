package com.dgsdddsample.usecase.show.factory

import com.dgsdddsample.domain.shared.ULIDGenerator
import com.dgsdddsample.domain.show.*

class ShowFactory(
    private val showRepository: ShowRepository,
    private val ulidGenerator: ULIDGenerator
) {
    fun build(
        title: String,
        releaseYear: Int,
    ): Show {
        val titleVO = Title(title)
        val releaseYearVO = ReleaseYear(releaseYear)

        if (showRepository.existBy(titleVO, releaseYearVO)) {
            throw IllegalArgumentException("title and releaseYear already exist")
        }

        return Show(
            id = ShowId(ulidGenerator.generate()),
            version = ShowVersion.init,
            title = titleVO,
            releaseYear = releaseYearVO,
        )
    }

    fun buildWithIdAndVersion(
        id: String,
        version: Int,
        title: String,
        releaseYear: Int,
    ): Show {
        val idVO = ShowId.from(id)
        val versionVO = ShowVersion(version)
        val titleVO = Title(title)
        val releaseYearVo = ReleaseYear(releaseYear)

        val show = showRepository.findById(idVO) ?: throw IllegalArgumentException("not found")

        if (show.version != versionVO) {
            throw IllegalArgumentException("invalid version")
        }

        return Show(
            id = idVO,
            version = versionVO.increment(),
            title = titleVO,
            releaseYear = releaseYearVo,
        )
    }
}
