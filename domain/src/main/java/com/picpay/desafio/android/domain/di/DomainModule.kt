package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.GetListUserUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetListUserUseCase(
            repository = get()
        )
    }
}