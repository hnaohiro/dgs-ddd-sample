package com.dgsdddsample.infrastructure.table

import org.jetbrains.exposed.sql.Table

object ShowTable : Table("shows") {
    val id = varchar("id", 26)
    val version = integer("version")
    val title = varchar("title", 100)
    val releaseYear = integer("release_year")

    override val primaryKey = PrimaryKey(id)
}
