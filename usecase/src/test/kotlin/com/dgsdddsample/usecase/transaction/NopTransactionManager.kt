package com.dgsdddsample.usecase.transaction

class NopTransactionManager : TransactionManager {
    override fun <T> transaction(statement: () -> T): T {
        return statement()
    }
}
