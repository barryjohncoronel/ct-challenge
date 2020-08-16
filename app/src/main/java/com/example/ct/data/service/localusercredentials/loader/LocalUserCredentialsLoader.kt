package com.example.ct.data.service.localusercredentials.loader

import com.example.ct.data.model.UserCredentials
import io.reactivex.Single

interface LocalUserCredentialsLoader {

    fun findUsernameCount(username: String): Single<Int>

    fun getCount(): Single<Int>

    fun getUserCredentials(username: String, password: String) : Single<UserCredentials>

}