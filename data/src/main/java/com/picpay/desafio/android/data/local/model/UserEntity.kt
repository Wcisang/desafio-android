package com.picpay.desafio.android.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "username") val username: String
)