package com.example.cartrack.ui.login.service

import android.content.Context
import com.example.cartrack.R
import com.example.cartrack.data.db.UserDao
import com.example.cartrack.data.db.model.User
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
                    Single.error(Throwable(context.getString(R.string.no_users_found)))
                } else {
                    userDao.getUser(username, password)
                        .onErrorResumeNext {
                            Single.error(Throwable(context.getString(R.string.invalid_credentials)))
                        }
                }
            }
    }
}