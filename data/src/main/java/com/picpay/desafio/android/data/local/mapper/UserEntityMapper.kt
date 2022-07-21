package com.picpay.desafio.android.data.local.mapper

import com.picpay.desafio.android.data.local.model.UserEntity
import com.picpay.desafio.android.domain.model.User

fun User.mapToUserEntity() = UserEntity (
    uid = id.toString(),
    img = img,
    name = name,
    username = username
)

fun UserEntity.mapToUser() = User(
    id = uid.toInt(),
    img = img,
    name = name,
    username = username
)