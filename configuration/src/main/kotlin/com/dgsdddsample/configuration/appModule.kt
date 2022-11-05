package com.dgsdddsample.configuration

import com.dgsdddsample.domain.shared.ULIDGenerator
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.infrastructure.KULIDGenerator
import com.dgsdddsample.infrastructure.queryservice.ShowsExposedQueryService
import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.infrastructure.transaction.ExposedTransactionManager
import com.dgsdddsample.usecase.show.*
import com.dgsdddsample.usecase.show.factory.ShowFactory
import com.dgsdddsample.usecase.show.service.ShowsQueryService
import com.dgsdddsample.usecase.transaction.TransactionManager
import org.koin.dsl.module

val appModule = module {
    single<TransactionManager> { ExposedTransactionManager() }
    single<ShowRepository> { ShowExposedRepository() }
    single<ULIDGenerator> { KULIDGenerator() }
    single<ShowsQueryService> { ShowsExposedQueryService() }
    single { CreateShowUseCase(get(), get(), get()) }
    single { DeleteShowUseCase(get(), get()) }
    single { GetShowsUseCase(get(), get()) }
    single { GetShowUseCase(get(), get()) }
    single { ShowFactory(get(), get()) }
    single { UpdateShowUseCase(get(), get(), get()) }
}
