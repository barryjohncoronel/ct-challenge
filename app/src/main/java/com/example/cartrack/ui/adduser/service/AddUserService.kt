package com.example.cartrack.ui.adduser.service

import io.reactivex.Completable

interface AddUserService {

    fun addUser(
        username: String,
        password: String
    ): Completable
}