package com.picpay.desafio.android.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object PicPayServiceHelper {

    private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    fun createPicPayService(): PicPayService {
        return createRetrofit().create(PicPayService::class.java)
    }

    private fun createRetrofit() = Retrofit.Builder()
        .baseUrl(URL)
        .client(createOkHttpClient())
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    private fun createOkHttpClient() = OkHttpClient.Builder().build()
}