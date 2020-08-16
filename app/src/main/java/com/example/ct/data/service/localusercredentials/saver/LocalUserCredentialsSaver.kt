package com.example.ct.data.service.localusercredentials.saver

import com.example.ct.data.model.UserCredentials
import io.reactivex.Completable

interface LocalUserCredentialsSaver {

    fun insert(userCredentials: UserCredentials): Completable
}