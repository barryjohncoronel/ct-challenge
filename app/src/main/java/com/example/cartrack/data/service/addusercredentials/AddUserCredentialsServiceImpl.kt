package com.example.cartrack.data.service.addusercredentials

import com.example.cartrack.data.db.UserCredentialsDao
import com.example.cartrack.data.exceptions.UserCredentialsAlreadyExistsException
import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Completable
import javax.inject.Inject

class AddUserCredentialsServiceImpl @Inject constructor(
    private val userCredentialsDao: UserCredentialsDao
) : AddUserCredentialsService {

    override fun addUserCredentials(username: String, password: String): Completable {
        return userCredentialsDao.findUsernameCount(username)
            .flatMapCompletable { count ->
                if (count == 0) {
                    userCredentialsDao.insert(
                        UserCredentials(
                            username = username,
                            password = password
                        )
                    )
                } else {
                    Completable.error(UserCredentialsAlreadyExistsException())
                }
            }
    }
}