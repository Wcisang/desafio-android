package com.picpay.desafio.android.data.local.dao

import androidx.room.*
import com.picpay.desafio.android.data.local.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getListUser(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

}
