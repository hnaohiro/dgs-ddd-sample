package com.dgsdddsample.infrastructure.transaction

import com.dgsdddsample.usecase.transaction.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction as exposedTransaction

class ExposedTransactionManager : TransactionManager {
    override fun <T> transaction(statement: () -> T): T {
        return exposedTransaction { statement() }
    }
}
