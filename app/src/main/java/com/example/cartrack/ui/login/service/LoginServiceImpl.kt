package com.example.cartrack.ui.login.service

import com.example.cartrack.data.db.UserDao
import com.example.cartrack.data.db.model.User
import io.reactivex.Single
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val userDao: UserDao
) : LoginService {

    companion object {
        const val INVALID_CREDENTIALS = "Invalid credentials."
        const val EMPTY_USER_TABLE = "No users found. Please add a user."
    }

    override fun login(
        username: String,
        password: String
    ): Single<User> {
        return userDao.getCount()
            .flatMap {
                if (it == 0) {
                    Single.error(Throwable(EMPTY_USER_TABLE))
                } else {
                    userDao.getUser(username, password)
                        .onErrorResumeNext {
                            Single.error(Throwable(INVALID_CREDENTIALS))
                        }
                }
            }
    }
}