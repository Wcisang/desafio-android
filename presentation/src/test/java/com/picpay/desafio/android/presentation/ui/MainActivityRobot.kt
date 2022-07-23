package com.picpay.desafio.android.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.picpay.desafio.android.domain.usecase.GetListUserUseCase
import com.picpay.desafio.android.presentation.R
import com.picpay.desafio.android.testUtils.PicPayDataFactory
import com.picpay.desafio.android.testUtils.RecyclerViewMatcher
import io.mockk.coEvery
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivityRobot {

    private val useCase: GetListUserUseCase = mockk()
    private val userList = PicPayDataFactory.createUserList()

    inner class Prepare {

        fun launch() {
            mockUserList()
            loadScreenKoinModule()
            val scenario = launchActivity<MainActivity>()
            scenario.onActivity {
                it.findViewById<RecyclerView>(R.id.rvList).apply {
                    measure(0, 0)
                    layout(0, 0, 400, 800)
                }
            }
        }

        private fun mockUserList() {
            coEvery { useCase.execute() } returns userList
        }

        private fun loadScreenKoinModule() {
            loadKoinModules(
                module {
                    viewModel { MainViewModel(useCase) }
                }
            )
        }

        infix fun assert(func: MainActivityRobot.Assert.() -> Unit) =
            Assert().apply(func)
    }

    inner class Assert {

        fun verifyFirstItem() {
            val userName = userList[0].username
            val name = userList[0].name
            getViewHolderReference(0).check(descendantAssertion(userName))
            getViewHolderReference(0).check(descendantAssertion(name))
        }

        private fun getViewHolderReference(position: Int) =
            Espresso.onView(
                RecyclerViewMatcher.withRecyclerView(R.id.rvList).atPosition(position)
            )

        private fun descendantAssertion(testingText: String): ViewAssertion? {
            return ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(testingText)))
        }
    }

    infix fun prepare(func: MainActivityRobot.Prepare.() -> Unit) =
        Prepare().apply(func)
}