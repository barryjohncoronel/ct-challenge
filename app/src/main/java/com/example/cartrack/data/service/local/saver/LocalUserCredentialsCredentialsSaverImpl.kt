package com.example.cartrack.data.service.local.saver

import com.example.cartrack.data.db.UserCredentialsDao
import com.example.cartrack.data.exceptions.UserCredentialsAlreadyExistsException
import com.example.cartrack.data.model.UserCredentials
import com.example.cartrack.data.service.local.loader.LocalUserCredentialsLoader
import io.reactivex.Completable
import javax.inject.Inject

class LocalUserCredentialsCredentialsSaverImpl @Inject constructor(
    private val userCredentialsLoader: LocalUserCredentialsLoader,
    private val userCredentialsDao: UserCredentialsDao
) : LocalUserCredentialsSaver {

    override fun insert(userCredentials: UserCredentials): Completable {
        return userCredentialsLoader
            .findUsernameCount(userCredentials.username)
            .flatMapCompletable {
                if (it == 0) {
                    userCredentialsDao.insert(userCredentials)
                } else {
                    Completable.error(UserCredentialsAlreadyExistsException())
                }
            }
    }
}