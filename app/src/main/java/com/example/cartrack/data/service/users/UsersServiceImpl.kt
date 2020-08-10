package com.example.cartrack.data.service.users

import com.example.cartrack.data.model.UserFromApi
import com.example.cartrack.data.network.ApiFactory
import com.example.cartrack.data.network.user.UserServiceApi
import com.example.cartrack.util.internet.InternetUtil
import io.reactivex.Single
import javax.inject.Inject

class UsersServiceImpl @Inject constructor(
    private val apiFactory: ApiFactory,
    private val internetUtil: InternetUtil
) : UsersService {

    override fun getUsers(): Single<List<UserFromApi>> {
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