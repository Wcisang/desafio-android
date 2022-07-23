package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.presentation.ui.MainActivity
import com.picpay.desafio.android.testUtils.RecyclerViewMatcher
import com.picpay.desafio.android.utils.PicPayMockServer
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivityRobot {

    private val server = PicPayMockServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var baseUrl : HttpUrl

    companion object {
        val okHttpClient = OkHttpClient.Builder().build()
    }

    fun mockRetrofit() {
        val networkModuleTest = module {
            factory {
                Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build().create(PicPayService::class.java)
            }
        }
        server.start()
        baseUrl = server.url()
        loadKoinModules(listOf(networkModuleTest))
    }

    fun stop() {
        scenario.close()
        server.close()
    }

    fun launch(funcPrepare: MainActivityRobot.Prepare.() -> Unit, funcAssert: MainActivityRobot.Assert.() -> Unit) {
        Prepare().apply(funcPrepare)
        launchActivity<MainActivity>().apply {
            scenario = this
            moveToState(Lifecycle.State.RESUMED)
            Assert().apply(funcAssert)
        }
    }

    inner class Prepare {

        fun mockSuccessResponse() {
            server.setSuccessResponse("user_list.json")
        }
    }

    inner class Assert {

        fun verifyListHeader() {
            val expectedTitle = context.getString(R.string.title)
            onView(ViewMatchers.withText(expectedTitle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun verifyFirstItem() {
            val userName = "@eduardo.santos"
            val name = "Eduardo Santos"
            getViewHolderReference(0).check(descendantAssertion(userName))
            getViewHolderReference(0).check(descendantAssertion(name))
        }

        private fun getViewHolderReference(position: Int) =
            onView(
                RecyclerViewMatcher.withRecyclerView(com.picpay.desafio.android.presentation.R.id.rvList).atPosition(position)
            )

        private fun descendantAssertion(testingText: String): ViewAssertion? {
            return ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(testingText)))
        }
    }
}