package com.picpay.desafio.android.presentation.di

import com.picpay.desafio.android.domain.usecase.GetListUserUseCase
import com.picpay.desafio.android.presentation.ui.MainViewModel
import com.picpay.desafio.android.testUtils.CoroutineTestRule
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import kotlin.test.assertNotNull

class PresentationModuleTest : AutoCloseKoinTest() {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        val supportModule = module {
            factory<GetListUserUseCase> { mockk() }
        }
       startKoin {
           modules(listOf(presentationModule, supportModule))
       }
    }

    @Test
    fun `SHOULD inject presentation module`() {
        val viewModel = get<MainViewModel>()
        assertNotNull(viewModel)
    }
}