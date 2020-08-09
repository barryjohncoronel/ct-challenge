package com.example.cartrack.data.service.adduser

import io.reactivex.Completable

interface AddUserService {

    fun addUser(
        username: String,
        password: String
    ): Completable
}