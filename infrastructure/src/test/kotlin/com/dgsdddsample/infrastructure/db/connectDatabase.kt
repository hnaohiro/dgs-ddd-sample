package com.dgsdddsample.infrastructure.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig

fun connectDatabase() {
    val host = System.getenv("DB_HOST") ?: "127.0.0.1"
    val port = System.getenv("DB_PORT") ?: "3306"
    val database = System.getenv("DB_DATABASE") ?: "dgs_ddd_sample_test"

    Database.connect(
        url = System.getenv("DB_URL") ?: "jdbc:mysql://$host:$port/${database}",
        user = System.getenv("DB_USER") ?: "root",
        password = System.getenv("DB_PASSWORD") ?: "root",
        databaseConfig = DatabaseConfig {
            defaultRepetitionAttempts = 1
        },
    )
}
