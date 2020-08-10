package com.example.cartrack.data.service.login

import com.example.cartrack.data.model.User
import io.reactivex.Single

interface LoginService {

    fun login(
        username: String,
        password: String
    ): Single<User>
}