package com.example.cartrack.data.service.login

import com.example.cartrack.data.db.model.User
import io.reactivex.Single

interface LoginService {

    fun login(
        username: String,
        password: String
    ): Single<User>
}