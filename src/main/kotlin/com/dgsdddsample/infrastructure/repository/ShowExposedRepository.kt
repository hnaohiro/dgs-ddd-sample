package com.dgsdddsample.infrastructure.repository

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.infrastructure.table.ShowTable
import org.jetbrains.exposed.sql.*

class ShowExposedRepository : ShowRepository {
    override fun findById(id: ShowId): Show? {
        return ShowTable
            .select { ShowTable.id eq id.string() }
            .limit(1)
            .singleOrNull()
            ?.let { reconstructFromRow(it) }
    }

    override fun findByTitle(titleFilter: String?): List<Show> {
        val shows = if (titleFilter != null) {
            ShowTable.select { ShowTable.title like LikePattern("%${titleFilter}%", '%') }
        } else {
            ShowTable.selectAll()
        }
        return shows.map { reconstructFromRow(it) }
    }

    private fun reconstructFromRow(row: ResultRow): Show {
        return Show.reconstruct(
            id = row[ShowTable.id],
            version = row[ShowTable.version],
            title = row[ShowTable.title],
            releaseYear = row[ShowTable.releaseYear]
        )
    }

    override fun save(show: Show): Boolean {
        if (show.version == ShowVersion.init) {
            val result = ShowTable.insert {
                it[id] = show.id.string()
                it[version] = show.version.int()
                it[title] = show.title.string()
                it[releaseYear] = show.releaseYear.int()
            }
            return result.insertedCount == 1
        } else {
            val updatedRow = ShowTable.update({ ShowTable.id eq show.id.string() }) {
                it[version] = show.version.int()
                it[title] = show.title.string()
                it[releaseYear] = show.releaseYear.int()
            }
            return updatedRow == 1
        }
    }

    override fun existBy(title: Title, releaseYear: ReleaseYear): Boolean {
        val count = ShowTable
            .select { (ShowTable.title eq title.string()) and (ShowTable.releaseYear eq releaseYear.int()) }
            .count()
        return count > 0
    }

    override fun delete(id: ShowId): Boolean {
        val count = ShowTable.deleteWhere {
            ShowTable.id eq id.string()
        }
        return count > 0
    }
}
