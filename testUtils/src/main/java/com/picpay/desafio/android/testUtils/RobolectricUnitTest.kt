package com.picpay.desafio.android.testUtils

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.unmockkAll
import org.junit.AfterClass
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class, sdk = [28])
abstract class RobolectricUnitTest : AutoCloseKoinTest() {

    companion object {

        @JvmStatic
        @AfterClass
        fun onDestroy() {
            stopKoin()
            unmockkAll()
        }
    }
}
