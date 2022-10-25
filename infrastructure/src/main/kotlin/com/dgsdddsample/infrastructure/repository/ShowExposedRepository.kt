package com.dgsdddsample.infrastructure.repository

import com.dgsdddsample.domain.show.*
import com.dgsdddsample.infrastructure.adapter.reconstructShow
import com.dgsdddsample.infrastructure.table.ShowTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ShowExposedRepository : ShowRepository {
    override fun findById(id: ShowId): Show? {
        return ShowTable
            .select { ShowTable.id eq id.string() }
            .limit(1)
            .singleOrNull()
            ?.reconstructShow()
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
