package com.example.cartrack.ui.login.service

import com.example.cartrack.data.db.model.User
import io.reactivex.Single

interface LoginService {

    fun login(
        username: String,
        password: String
    ): Single<User>
}