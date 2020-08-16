package com.example.ct.data.service.localusercredentials.saver

import com.example.ct.data.db.UserCredentialsDao
import com.example.ct.util.UserCredentialsAlreadyExistsException
import com.example.ct.data.model.UserCredentials
import com.example.ct.data.service.localusercredentials.loader.LocalUserCredentialsLoader
import io.reactivex.Completable
import javax.inject.Inject

class LocalUseCredentialsSaverImpl @Inject constructor(
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