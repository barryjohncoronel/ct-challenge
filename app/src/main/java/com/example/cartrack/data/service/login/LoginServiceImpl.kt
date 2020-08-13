package com.example.cartrack.data.service.login

import android.content.Context
import com.example.cartrack.data.db.UserCredentialsDao
import com.example.cartrack.data.exceptions.InvalidUserCredentialsException
import com.example.cartrack.data.exceptions.NoUserCredentialsFoundException
import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Single
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val context: Context,
    private val userCredentialsDao: UserCredentialsDao
) : LoginService {

    override fun login(
        username: String,
        password: String
    ): Single<UserCredentials> {
        return userCredentialsDao.getCount()
            .flatMap {
                if (it == 0) {
                    Single.error(NoUserCredentialsFoundException())
                } else {
                    userCredentialsDao.getUserCredentials(username, password)
                        .onErrorResumeNext {
                            Single.error(InvalidUserCredentialsException())
                        }
                }
            }
    }
}