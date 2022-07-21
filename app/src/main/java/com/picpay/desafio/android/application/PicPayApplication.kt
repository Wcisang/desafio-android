package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.data.di.dataModule
import com.picpay.desafio.android.domain.di.domainModule
import com.picpay.desafio.android.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startDI()
    }

    private fun startDI() {
        startKoin {
            androidContext(this@PicPayApplication)
            loadKoinModules(
                listOf(domainModule, dataModule, presentationModule))
        }
    }
}