package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.PicPayRepository

class GetListUserUseCase(
    private val repository: PicPayRepository
) : UseCase<List<User>, Nothing> {

    override suspend fun execute(params: Nothing): List<User> {
        return repository.getUsersList()
    }
}