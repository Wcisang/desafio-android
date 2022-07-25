package com.picpay.desafio.android.security.di

import com.picpay.desafio.android.security.detectors.PicPayRootDetectorsFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val securityModule = module {

    factory {
        PicPayRootDetectorsFactory.createDetectorsList(androidContext())
    }
}