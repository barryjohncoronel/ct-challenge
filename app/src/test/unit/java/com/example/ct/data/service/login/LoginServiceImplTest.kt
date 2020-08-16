package com.example.ct.data.service.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ct.UserCredentialsFactory
import com.example.ct.util.InvalidUserCredentialsException
import com.example.ct.util.NoUserCredentialsFoundException
import com.example.ct.data.model.UserCredentials
import com.example.ct.data.service.localusercredentials.loader.LocalUserCredentialsLoader
import com.example.ct.util.AppConstants.INVALID_CREDENTIALS
import com.example.ct.util.AppConstants.NO_USERS_FOUND
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginServiceImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var localUserCredentialsLoader: LocalUserCredentialsLoader

    lateinit var classUnderTest: LoginServiceImpl

    @Before
    fun setUp() {
        classUnderTest = LoginServiceImpl(localUserCredentialsLoader)
    }

    @After
    fun tearDown() {
        Mockito.reset(localUserCredentialsLoader)
    }

    @Test
    fun shouldReturnNoUserCredentialsFoundException() {
        val userCredentials = UserCredentialsFactory.userCredentials()

        given(localUserCredentialsLoader.getUserCredentials(userCredentials.username, userCredentials.password))
            .willReturn(Single.error { NoUserCredentialsFoundException() })

        classUnderTest
            .login(userCredentials.username, userCredentials.password)
            .test()
            .assertNotComplete()
            .assertError(NoUserCredentialsFoundException::class.java)
            .assertErrorMessage(NO_USERS_FOUND)
    }

    @Test
    fun shouldReturnInvalidUserCredentialsException() {
        val userCredentials = UserCredentialsFactory.userCredentials()

        given(localUserCredentialsLoader.getUserCredentials(userCredentials.username, userCredentials.password))
            .willReturn(Single.error { InvalidUserCredentialsException() })

        classUnderTest
            .login(userCredentials.username, userCredentials.password)
            .test()
            .assertNotComplete()
            .assertError(InvalidUserCredentialsException::class.java)
            .assertErrorMessage(INVALID_CREDENTIALS)
    }

    @Test
    fun shouldReturnUserCredentials() {
        val expected = UserCredentialsFactory.userCredentials()

        given(localUserCredentialsLoader.getUserCredentials(expected.username, expected.password))
            .willReturn(Single.fromCallable { expected })

        val testObserver = TestObserver<UserCredentials>()

        classUnderTest
            .login(expected.username, expected.password)
            .subscribe(testObserver)

        testObserver
            .assertComplete()
            .assertNoErrors()

        val actual = testObserver.values()[0]

        assertThat(actual.username).isEqualTo(expected.username)
        assertThat(actual.password).isEqualTo(expected.password)
    }
}