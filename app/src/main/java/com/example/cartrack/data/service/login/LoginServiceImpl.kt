package com.example.cartrack.data.service.login

import android.content.Context
import com.example.cartrack.data.db.UserDao
import com.example.cartrack.data.exceptions.InvalidUserException
import com.example.cartrack.data.exceptions.NoUserFoundException
import com.example.cartrack.data.model.User
import io.reactivex.Single
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val context: Context,
    private val userDao: UserDao
) : LoginService {

    override fun login(
        username: String,
        password: String
    ): Single<User> {
        return userDao.getCount()
            .flatMap {
                if (it == 0) {
                    Single.error(NoUserFoundException())
                } else {
                    userDao.getUser(username, password)
                        .onErrorResumeNext {
                            Single.error(InvalidUserException())
                        }
                }
            }
    }
}