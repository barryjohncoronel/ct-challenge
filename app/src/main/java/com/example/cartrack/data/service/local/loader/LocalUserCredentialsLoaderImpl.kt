package com.example.cartrack.data.service.local.loader

import com.example.cartrack.data.db.UserCredentialsDao
import com.example.cartrack.data.exceptions.NoUserCredentialsFoundException
import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Single
import javax.inject.Inject

class LocalUserCredentialsLoaderImpl @Inject constructor(
    private val userCredentialsDao: UserCredentialsDao
) : LocalUserCredentialsLoader {

    override fun getCount(): Single<Int> {
        return userCredentialsDao.getCount()
    }

    override fun findUsernameCount(username: String): Single<Int> {
        return userCredentialsDao.findUsernameCount(username)
    }

    override fun getUserCredentials(username: String, password: String): Single<UserCredentials> {
        return userCredentialsDao
            .getUserCredentials(username, password)
            .onErrorResumeNext {
                Single.error(NoUserCredentialsFoundException())
            }
    }
}