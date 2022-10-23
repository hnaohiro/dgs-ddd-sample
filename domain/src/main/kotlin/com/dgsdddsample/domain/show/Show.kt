package com.dgsdddsample.domain.show

data class Show(
    val id: ShowId,
    val version: ShowVersion,
    val title: Title,
    val releaseYear: ReleaseYear,
) {
    companion object {
        fun reconstruct(
            id: String,
            version: Int,
            title: String,
            releaseYear: Int,
        ): Show {
            return Show(
                id = ShowId.from(id),
                version = ShowVersion(version),
                title = Title(title),
                releaseYear = ReleaseYear(releaseYear)
            )
        }
    }
}
