package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.PicPayRepository
import com.picpay.desafio.android.testUtils.PicPayDataFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


internal class GetListUserUseCaseTest {

    private val repository: PicPayRepository = mockk()
    private lateinit var useCase: GetListUserUseCase

    @Before
    fun setUp() {
        useCase = GetListUserUseCase(repository)
    }

    @Test
    fun `WHEN call get user list use case THEN return user list`() = runBlockingTest {
        val list = PicPayDataFactory.createUserList()
        coEvery { repository.getUsersList() } returns list

        val returnedList = useCase.execute()

        assertEquals(list, returnedList)
    }
}