package com.example.cartrack.data.service.local.saver

import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Completable

interface LocalUserCredentialsSaver {

    fun insert(userCredentials: UserCredentials): Completable
}