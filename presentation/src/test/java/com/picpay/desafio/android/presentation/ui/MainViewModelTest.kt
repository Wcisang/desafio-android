package com.picpay.desafio.android.presentation.ui

import com.picpay.desafio.android.domain.usecase.GetListUserUseCase
import com.picpay.desafio.android.testUtils.CoroutineTestRule
import com.picpay.desafio.android.testUtils.PicPayDataFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

internal class MainViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var viewModel: MainViewModel
    private val useCase: GetListUserUseCase = mockk()

    @Test
    fun `WHEN start viewModel THEN set state to success with list`() = runBlocking {
        val userList = PicPayDataFactory.createUserList()
        coEvery { useCase.execute() } returns userList
        viewModel = MainViewModel(useCase)

        assert(viewModel.uiState.value is MainActivityState.SuccessListState)
        assertEquals((viewModel.uiState.value as MainActivityState.SuccessListState).users, userList)
    }
}