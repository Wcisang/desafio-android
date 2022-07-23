package com.picpay.desafio.android.testUtils

import android.app.Application
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_AppCompat_NoActionBar)
        startKoin {
            modules(emptyList())
        }
    }
}
