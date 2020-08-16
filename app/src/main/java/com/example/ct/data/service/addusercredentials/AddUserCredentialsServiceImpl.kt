package com.example.ct.data.service.addusercredentials

import com.example.ct.data.model.UserCredentials
import com.example.ct.data.service.localusercredentials.saver.LocalUserCredentialsSaver
import io.reactivex.Completable
import javax.inject.Inject

class AddUserCredentialsServiceImpl @Inject constructor(
    private val localUserCredentialsSaver: LocalUserCredentialsSaver
) : AddUserCredentialsService {

    override fun addUserCredentials(username: String, password: String): Completable {
        return localUserCredentialsSaver.insert(
            UserCredentials(
                username = username,
                password = password
            )
        )
    }
}