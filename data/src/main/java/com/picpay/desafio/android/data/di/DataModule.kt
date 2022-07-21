package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.local.database.PicPayDataBase
import com.picpay.desafio.android.data.local.database.PicPayDataBaseHelper
import com.picpay.desafio.android.data.remote.PicPayServiceHelper
import com.picpay.desafio.android.data.repository.PicPayRepositoryImpl
import com.picpay.desafio.android.domain.repository.PicPayRepository
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

val dataModule = module {

    single {
        PicPayDataBaseHelper.createDatabase(
            context = androidContext()
        )
    }

    factory {
        get<PicPayDataBase>().userDao()
    }

    factory {
        PicPayServiceHelper.createPicPayService()
    }

    factory<PicPayRepository> {
        PicPayRepositoryImpl(
            remote = get(),
            local = get()
        )
    }
}