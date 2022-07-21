package com.picpay.desafio.android.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class PicPayDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
