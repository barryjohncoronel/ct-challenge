package com.example.cartrack.data.service.login

import com.example.cartrack.data.model.UserCredentials
import com.example.cartrack.data.service.local.loader.LocalUserCredentialsLoader
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