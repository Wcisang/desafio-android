package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.domain.model.User

interface PicPayRepository {

    suspend fun getUsersList() : List<User>

}