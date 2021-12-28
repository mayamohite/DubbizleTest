package com.example.dubizzletest.productlist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.dubizzletest.MockServerDispatcher
import com.example.dubizzletest.R
import com.example.dubizzletest.di.UrlProviderModule
import com.example.dubizzletest.launchFragmentInHiltContainer
import com.example.dubizzletest.presentation.productlist.ProductListFragment
import com.example.dubizzletest.presentation.productlist.ProductRecyclerAdapter
import com.example.dubizzletest.presentation.util.EspressoIdlingResource
import com.example.dubizzletest.presentation.util.ImageCache
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Fragment testing by using mock server
 */
@UninstallModules(
    UrlProviderModule::class,
)
@HiltAndroidTest
class ProductListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // initialise web server always here. Do not initialise in @Before. It will not work properly.
    private val mockWebServer = MockWebServer()

    @Module
    @InstallIn(SingletonComponent::class)
    class FakeBaseUrlModule {

        @Provides
        @Singleton
        fun provideUrl(): String = "http://localhost:8080/"
    }

    @Inject
    lateinit var okHttp: OkHttpClient

    @Inject
    lateinit var imageCache: ImageCache

    @Before
    fun setUp() {
        hiltRule.inject()
        imageCache.initializeCache()
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttp))
    }

    @Test
    fun testSuccessResponse() {
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()

        launchFragmentInHiltContainer<ProductListFragment>()

        onView(withId(R.id.rv_product))
            .perform(
                RecyclerViewActions.scrollTo<ProductRecyclerAdapter.ProductViewHolder>(
                    hasDescendant(withText("Notebook"))
                )
            )

        onView(withId(R.id.rv_product))
            .perform(
                RecyclerViewActions.scrollTo<ProductRecyclerAdapter.ProductViewHolder>(
                    hasDescendant(withText("AED 5"))
                )
            )
    }
}
