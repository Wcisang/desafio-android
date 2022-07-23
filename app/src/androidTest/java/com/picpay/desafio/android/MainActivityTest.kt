package com.picpay.desafio.android

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.picpay.desafio.android.utils.OkHttpIdlingResourceRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    private lateinit var robot: MainActivityRobot

    @get:Rule
    var rule = OkHttpIdlingResourceRule(MainActivityRobot.okHttpClient)

    @Before
    fun setup() {
        robot = MainActivityRobot()
        robot.mockRetrofit()
    }

    @After
    fun tearDown() {
        robot.stop()
    }

    @Test
    fun shouldDisplayTitle() {
        robot.launch(
            funcPrepare = {
                mockSuccessResponse()
                          },
            funcAssert = {
                verifyListHeader()
            }
        )
    }

    @Test
    fun shouldDisplayListItem() {
        robot.launch(
            funcPrepare = {
                mockSuccessResponse()
            },
            funcAssert = {
                verifyFirstItem()
            }
        )
    }
}