package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.mapper.mapToUser
import com.picpay.desafio.android.data.local.mapper.mapToUserEntity
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.PicPayRepository

class PicPayRepositoryImpl(
    private val remote: PicPayService,
    private val local: UserDao
) : PicPayRepository {

    override suspend fun getUsersList(): List<User> {
        return try {
            val list = remote.getUsers()
            local.insertAll(list.map { it.mapToUserEntity() })
            list
        }catch (e: Exception) {
            local.getListUser().map {
                it.mapToUser()
            }
        }
    }
}