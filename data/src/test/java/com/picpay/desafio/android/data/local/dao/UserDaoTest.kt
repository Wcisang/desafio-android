package com.picpay.desafio.android.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.data.local.database.PicPayDataBase
import com.picpay.desafio.android.data.local.mapper.mapToUserEntity
import com.picpay.desafio.android.testUtils.PicPayDataFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@Config(sdk = [28])
internal class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: PicPayDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, PicPayDataBase::class.java
        ).allowMainThreadQueries().build()

        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    fun `WHEN insert items in dao THEN same items should return`() = runBlocking {
        val list = PicPayDataFactory.createUserList().map { it.mapToUserEntity() }

        userDao.insertAll(list)

        val daoList = userDao.getListUser()

        assert(daoList.containsAll(list))
    }

    @Test
    fun `WHEN insert the same items THEN replace items`() = runBlocking {
        val list = PicPayDataFactory.createUserList().map { it.mapToUserEntity() }

        userDao.insertAll(list)
        userDao.insertAll(list)

        val daoList = userDao.getListUser()

        assert(daoList.containsAll(list))
    }
}