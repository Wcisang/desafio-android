package com.picpay.desafio.android.utils

import okhttp3.HttpUrl
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

class PicPayMockServer {

    private val server = MockWebServer()

    fun start() {
        server.start(serverPort)
    }

    fun close() {
        server.close()
    }

    fun url(): HttpUrl {
        return server.url("/")
    }

    fun setSuccessResponse(fileName: String) {
        server.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return when (request.path) {
                    "/users" -> createSuccessMockResponse(fileName)
                    else -> errorResponse
                }
            }
        }
    }

    private fun createSuccessMockResponse(fileName: String) : MockResponse {
        val body = FileReader.readStringFromFile(fileName)
        return MockResponse()
            .setResponseCode(200)
            .setBody(body)
    }

    companion object {
        private const val serverPort = 8080
        private val errorResponse by lazy { MockResponse().setResponseCode(404) }
    }

}