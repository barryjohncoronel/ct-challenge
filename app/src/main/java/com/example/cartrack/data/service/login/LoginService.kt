package com.example.cartrack.data.service.login

import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Single

interface LoginService {

    fun login(
        username: String,
        password: String
    ): Single<UserCredentials>
}