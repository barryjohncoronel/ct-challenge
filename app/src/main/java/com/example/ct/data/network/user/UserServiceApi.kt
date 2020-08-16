package com.example.ct.data.network.user

import com.example.ct.data.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserServiceApi {

    @GET("users")
    fun getUsers(): Single<Response<List<User>>>
}