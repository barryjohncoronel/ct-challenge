package com.example.ct.data.service.login

import com.example.ct.data.model.UserCredentials
import io.reactivex.Single

interface LoginService {

    fun login(
        username: String,
        password: String
    ): Single<UserCredentials>
}