package com.example.ct.data.service.addusercredentials

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ct.UserCredentialsFactory
import com.example.ct.util.UserCredentialsAlreadyExistsException
import com.example.ct.data.service.localusercredentials.saver.LocalUserCredentialsSaver
import com.example.ct.util.AppConstants.USER_ALREADY_EXISTS
import com.nhaarman.mockitokotlin2.given
import io.reactivex.Completable
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddUserCredentialsImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var localUserCredentialsSaver: LocalUserCredentialsSaver

    lateinit var classUnderTest: AddUserCredentialsServiceImpl

    @Before
    fun setUp() {
        classUnderTest = AddUserCredentialsServiceImpl(localUserCredentialsSaver)
    }

    @After
    fun tearDown() {
        Mockito.reset(localUserCredentialsSaver)
    }

    @Test
    fun shouldReturnUserCredentialsAlreadyExistsException() {
        val userCredentials = UserCredentialsFactory.userCredentials()

        given(localUserCredentialsSaver.insert(userCredentials))
            .willReturn(Completable.error { UserCredentialsAlreadyExistsException() })

        classUnderTest
            .addUserCredentials(userCredentials.username, userCredentials.password)
            .test()
            .assertNotComplete()
            .assertError(UserCredentialsAlreadyExistsException::class.java)
            .assertErrorMessage(USER_ALREADY_EXISTS)
    }

    fun shouldSuccessfullyInsertUser() {
        val userCredentials = UserCredentialsFactory.userCredentials()

        given(localUserCredentialsSaver.insert(userCredentials))
            .willReturn(Completable.complete())

        classUnderTest
            .addUserCredentials(userCredentials.username, userCredentials.password)
            .test()
            .assertComplete()
            .assertNoErrors()
    }
}