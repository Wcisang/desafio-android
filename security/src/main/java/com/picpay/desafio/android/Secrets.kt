package com.picpay.desafio.android

class Secrets {

    // Method calls will be added by gradle task hideSecret
    // Example : external fun getWellHiddenSecret(packageName: String): String

    companion object {
        init {
            System.loadLibrary("secrets")
        }
    }

    external fun getsignkey(packageName: String): String

    external fun getsignappkey(packageName: String): String
}