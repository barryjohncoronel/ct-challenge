package com.example.ct.data.service.localusercredentials.loader

import com.example.ct.data.db.UserCredentialsDao
import com.example.ct.util.NoUserCredentialsFoundException
import com.example.ct.data.model.UserCredentials
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