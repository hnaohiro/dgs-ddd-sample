package com.dgsdddsample.presentation

import com.dgsdddsample.domain.shared.ULIDGenerator
import com.dgsdddsample.domain.show.ShowRepository
import com.dgsdddsample.infrastructure.KULIDGenerator
import com.dgsdddsample.infrastructure.repository.ShowExposedRepository
import com.dgsdddsample.presentation.adapter.ShowAdapter
import com.dgsdddsample.usecase.show.*
import org.koin.dsl.module

val appModule = module {
    single<ShowRepository> { ShowExposedRepository() }
    single<ULIDGenerator> { KULIDGenerator() }
    single { CreateShowUseCase(get(), get()) }
    single { DeleteShowUseCase(get()) }
    single { GetShowsUseCase(get()) }
    single { GetShowUseCase(get()) }
    single { ShowFactory(get(), get()) }
    single { UpdateShowUseCase(get(), get()) }
    single { ShowAdapter() }
}
