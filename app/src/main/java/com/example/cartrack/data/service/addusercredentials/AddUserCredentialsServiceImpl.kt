package com.example.cartrack.data.service.addusercredentials

import com.example.cartrack.data.model.UserCredentials
import com.example.cartrack.data.service.local.saver.LocalUserCredentialsSaver
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