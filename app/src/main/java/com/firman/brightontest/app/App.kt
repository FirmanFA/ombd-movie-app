package com.firman.brightontest.app

import android.app.Application
import com.firman.brightontest.di.api
import com.firman.brightontest.di.repositories
import com.firman.brightontest.di.useCases
import com.firman.brightontest.di.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()

            androidContext(this@App)

            modules(
                useCases,
                api,
                viewModels,
                repositories,
            )
        }

    }
}