package com.example.cartrack.data.service.users

import com.example.cartrack.data.network.ApiFactory
import com.example.cartrack.data.network.user.UserFromApi
import com.example.cartrack.data.network.user.UserServiceApi
import io.reactivex.Single
import javax.inject.Inject

class UsersServiceImpl @Inject constructor(
    private val apiFactory: ApiFactory
) : UsersService {

    override fun getUsers(): Single<List<UserFromApi>> {
        return apiFactory.api(UserServiceApi::class.java)
            .flatMap {
                it.getUsers()
            }.flatMap {
                Single.fromCallable {
                    it.body()
                }
            }
    }
}