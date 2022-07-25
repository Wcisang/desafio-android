package com.picpay.desafio.android.application

import android.app.Application
import com.picpay.desafio.android.data.di.dataModule
import com.picpay.desafio.android.domain.di.domainModule
import com.picpay.desafio.android.presentation.di.presentationModule
import com.picpay.desafio.android.security.SecurityManager
import com.picpay.desafio.android.security.di.securityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import java.lang.IllegalArgumentException

class PicPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startDI()
        startSecuritySearch()
    }

    private fun startDI() {
        startKoin {
            androidContext(this@PicPayApplication)
            loadKoinModules(
                listOf(domainModule, dataModule, presentationModule, securityModule))
        }
    }

    private fun startSecuritySearch() {
        val manager = SecurityManager(this)
        manager.isASafeDevice()
    }
}