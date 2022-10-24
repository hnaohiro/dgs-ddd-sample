package com.dgsdddsample.usecase.transaction

interface TransactionManager {
    fun <T> transaction(statement: () -> T): T
}
