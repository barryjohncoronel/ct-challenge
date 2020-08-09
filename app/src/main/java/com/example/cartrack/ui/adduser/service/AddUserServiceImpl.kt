package com.example.cartrack.ui.adduser.service

import android.content.Context
import com.example.cartrack.R
import com.example.cartrack.data.db.UserDao
import com.example.cartrack.data.db.model.User
import io.reactivex.Completable
import javax.inject.Inject

class AddUserServiceImpl @Inject constructor(
    private val context: Context,
    private val userDao: UserDao
) : AddUserService {


    override fun addUser(username: String, password: String): Completable {
        return userDao.findUsernameCount(username)
            .flatMapCompletable { count ->
                if (count == 0) {
                    userDao.insert(
                        User(
                            username = username,
                            password = password
                        )
                    )
                } else {
                    Completable.error(Throwable(context.getString(R.string.username_already_exists)))
                }
            }

    }
}