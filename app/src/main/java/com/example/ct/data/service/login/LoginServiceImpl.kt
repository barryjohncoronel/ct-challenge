package com.example.ct.data.service.login

import com.example.ct.data.model.UserCredentials
import com.example.ct.data.service.localusercredentials.loader.LocalUserCredentialsLoader
import io.reactivex.Single
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val localUserCredentialsLoader: LocalUserCredentialsLoader
) : LoginService {

    override fun login(
        username: String,
        password: String
    ): Single<UserCredentials> {
        return localUserCredentialsLoader.getUserCredentials(username, password)
    }
}