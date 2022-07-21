package com.picpay.desafio.android

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<com.picpay.desafio.android.domain.model.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}