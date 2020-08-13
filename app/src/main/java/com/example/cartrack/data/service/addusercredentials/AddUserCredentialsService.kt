package com.example.cartrack.data.service.addusercredentials

import io.reactivex.Completable

interface AddUserCredentialsService {

    fun addUserCredentials(
        username: String,
        password: String
    ): Completable
}