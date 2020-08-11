package com.example.cartrack.data.service.adduser

import android.content.Context
import com.example.cartrack.data.db.UserDao
import com.example.cartrack.data.exceptions.UserAlreadyExistsException
import com.example.cartrack.data.model.User
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
                    Completable.error(UserAlreadyExistsException())
                }
            }
    }
}