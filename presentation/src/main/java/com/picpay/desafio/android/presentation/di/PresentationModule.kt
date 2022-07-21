package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.presentation.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel(
            useCase = get()
        )
    }
}