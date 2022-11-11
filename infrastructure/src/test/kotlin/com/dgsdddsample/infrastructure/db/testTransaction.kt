package com.dgsdddsample.infrastructure.db

import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

fun <T> testTransaction(statement: Transaction.() -> T): T {
    return transaction {
        statement().apply { rollback() }
    }
}
