package com.example.ct.data.service.users

import com.example.ct.data.model.User
import com.example.ct.data.network.ApiFactory
import com.example.ct.data.network.user.UserServiceApi
import com.example.ct.util.internet.InternetUtil
import io.reactivex.Single
import javax.inject.Inject

class UsersServiceImpl @Inject constructor(
    private val apiFactory: ApiFactory,
    private val internetUtil: InternetUtil
) : UsersService {

    override fun getUsers(): Single<List<User>> {
        return internetUtil.isNetworkAvailable()
            .flatMap {
                apiFactory.api(UserServiceApi::class.java)
            }.flatMap {
                it.getUsers()
            }.flatMap {
                Single.fromCallable {
                    it.body()
                }
            }
    }
}