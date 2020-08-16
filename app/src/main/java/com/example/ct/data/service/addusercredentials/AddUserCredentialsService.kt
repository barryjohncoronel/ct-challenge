package com.example.ct.data.service.addusercredentials

import io.reactivex.Completable

interface AddUserCredentialsService {

    fun addUserCredentials(
        username: String,
        password: String
    ): Completable
}