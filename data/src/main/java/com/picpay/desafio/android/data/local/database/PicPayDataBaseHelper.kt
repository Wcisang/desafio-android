package com.picpay.desafio.android.data.local.database

import android.content.Context
import androidx.room.Room

object PicPayDataBaseHelper {

    private const val dataBaseName = "picpay-database"

    fun createDatabase(context: Context): PicPayDataBase {
        return Room.databaseBuilder(
            context,
            PicPayDataBase::class.java, dataBaseName
        ).build()
    }

}