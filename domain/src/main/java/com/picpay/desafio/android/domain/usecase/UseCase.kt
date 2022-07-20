package com.picpay.desafio.android.domain.usecase

interface UseCase<T, in Params> {

    suspend fun execute(params: Params): T
}