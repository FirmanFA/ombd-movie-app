package com.firman.brightontest.di

import com.firman.brightontest.repository.main.MainRepository
import com.firman.brightontest.repository.main.MainRepositoryImpl
import org.koin.dsl.module

val repositories = module {
    single<MainRepository> { MainRepositoryImpl(get(),
//        get()
    ) }
}