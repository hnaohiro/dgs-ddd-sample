package com.dgsdddsample.domain.show

interface ShowRepository {
    fun findById(id: ShowId): Show?
    fun findByTitle(titleFilter: String?): List<Show>
    fun save(show: Show): Boolean
    fun existBy(title: Title, releaseYear: ReleaseYear): Boolean
    fun delete(id: ShowId): Boolean
}
