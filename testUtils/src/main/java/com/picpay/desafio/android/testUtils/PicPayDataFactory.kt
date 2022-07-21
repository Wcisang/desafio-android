package com.picpay.desafio.android.testUtils

import com.picpay.desafio.android.domain.model.User

object PicPayDataFactory {

    private const val userListSize = 3

    fun createUser() = User (
        img = "img",
        name = "name",
        id = 1,
        username = "username"
    )

    fun createUserList() : List<User> {
        val list = mutableListOf<User>()
        repeat(userListSize){
            list.add(createUser())
        }
        return list
    }
}