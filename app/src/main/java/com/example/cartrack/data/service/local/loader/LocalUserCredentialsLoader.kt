package com.example.cartrack.data.service.local.loader

import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Single

interface LocalUserCredentialsLoader {

    fun findUsernameCount(username: String): Single<Int>

    fun getCount(): Single<Int>

    fun getUserCredentials(username: String, password: String) : Single<UserCredentials>

}