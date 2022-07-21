package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.mapper.mapToUserEntity
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.testUtils.PicPayDataFactory
import com.picpay.desafio.android.testUtils.coVerifyOnce
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


internal class PicPayRepositoryTest {

    private val remote: PicPayService = mockk()
    private val local: UserDao = mockk()
    private lateinit var repository: PicPayRepository

    @Before
    fun setUp() {
        repository = PicPayRepositoryImpl(remote, local)
    }

    @Test
    fun `WHEN remotes get a error THEN get list from local`() = runBlockingTest {
        val list = PicPayDataFactory.createUserList()
        coEvery { remote.getUsers() } throws Exception()
        coEvery { local.getListUser() } returns list.map { it.mapToUserEntity() }

        val returnedList = repository.getUsersList()

        coVerifyOnce { local.getListUser() }

        assert(returnedList.containsAll(list))
    }

    @Test
    fun `WHEN remotes return a list THEN save local and return`() = runBlockingTest {
        val list = PicPayDataFactory.createUserList()
        coEvery { remote.getUsers() } returns list
        coEvery { local.insertAll(any()) } returns Unit

        val returnedList = repository.getUsersList()

        coVerifyOnce { local.insertAll(any()) }

        assert(returnedList.containsAll(list))
    }
}