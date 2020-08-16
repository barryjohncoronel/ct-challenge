package com.example.ct

import com.example.ct.BaseFactory.Companion.randomString
import com.example.ct.data.model.UserCredentials

class UserCredentialsFactory {

    companion object {
        fun userCredentials(): UserCredentials {
            return UserCredentials(
                username = randomString(),
                password = randomString()
            )
        }
    }
}