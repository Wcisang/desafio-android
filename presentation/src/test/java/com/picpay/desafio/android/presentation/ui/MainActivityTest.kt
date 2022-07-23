package com.picpay.desafio.android.presentation.ui

import com.picpay.desafio.android.testUtils.RobolectricUnitTest
import org.junit.Before
import org.junit.Test

class MainActivityTest : RobolectricUnitTest() {

    private lateinit var robot: MainActivityRobot

    @Before
    fun setUp() {
        robot = MainActivityRobot()
    }

    @Test
    fun `WHEN start activity THEN show user list`() {
        robot.prepare {
            launch()
        } assert {
            verifyFirstItem()
        }
    }
}