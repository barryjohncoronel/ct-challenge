package com.example.cartrack

import com.example.cartrack.BaseFactory.Companion.randomString
import com.example.cartrack.data.model.UserCredentials

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