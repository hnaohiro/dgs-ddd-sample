package com.dgsdddsample.infrastructure.adapter

import com.dgsdddsample.domain.show.Show
import com.dgsdddsample.infrastructure.table.ShowTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.reconstructShow(): Show {
    return Show.reconstruct(
        id = this[ShowTable.id],
        version = this[ShowTable.version],
        title = this[ShowTable.title],
        releaseYear = this[ShowTable.releaseYear]
    )
}
