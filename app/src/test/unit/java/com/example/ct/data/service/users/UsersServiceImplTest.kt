package com.example.ct.data.service.users

import com.example.ct.MockWebServiceRule
import com.example.ct.UserFactory
import com.example.ct.util.NoInternetException
import com.example.ct.data.model.User
import com.example.ct.data.network.ApiFactory
import com.example.ct.data.network.user.UserServiceApi
import com.example.ct.util.AppConstants.NO_INTERNET_CONNECTION
import com.example.ct.util.internet.InternetUtil
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection.HTTP_OK

@RunWith(MockitoJUnitRunner::class)
class UsersServiceImplTest {

    @get:Rule
    var rule = MockWebServiceRule()

    @Mock
    lateinit var apiFactory: ApiFactory

    @Mock
    lateinit var internetUtil: InternetUtil

    private lateinit var classUnderTest: UsersServiceImpl

    @Before
    fun setUp() {
        classUnderTest = UsersServiceImpl(apiFactory, internetUtil)
    }

    @After
    fun tearDown() {
        Mockito.reset(apiFactory, internetUtil)
    }

    @Test
    fun getUsersWillSucceed() {
        provideInternetNetworkAvailable()
        provideMockUsersServiceApi()

        val expected = UserFactory.users()

        rule.server()
            .enqueue(
                MockResponse()
                    .setResponseCode(HTTP_OK)
                    .setBody(rule.toBody(expected))
            )

        val testObserver = TestObserver<List<User>>()

        classUnderTest
            .getUsers()
            .subscribe(testObserver)

        testObserver
            .assertComplete()
            .assertNoErrors()

        val actual = testObserver.values()[0]

        assertThat(actual).isNotNull

        assertThat(actual).hasSameSizeAs(expected)

        actual.forEachIndexed { index, user ->
            assertThat(user).isEqualTo(expected[index])
        }
    }

    @Test
    fun getUsersWillReturnNoInternetException() {
        provideInternetNetworkUnAvailable()
        provideMockUsersServiceApi()

        classUnderTest
            .getUsers()
            .test()
            .assertNotComplete()
            .assertError(NoInternetException::class.java)
            .assertErrorMessage(NO_INTERNET_CONNECTION)
    }

    private fun provideInternetNetworkAvailable() {
        given(internetUtil.isNetworkAvailable())
            .willReturn(Single.fromCallable { true })
    }


    private fun provideInternetNetworkUnAvailable() {
        given(internetUtil.isNetworkAvailable())
            .willReturn(Single.error { NoInternetException() })
    }

    private fun provideMockUsersServiceApi() {
        given(apiFactory.api(UserServiceApi::class.java))
            .willReturn(
                Single.fromCallable {
                    rule.create(
                        UserServiceApi::class.java
                    )
                }
            )
    }
}