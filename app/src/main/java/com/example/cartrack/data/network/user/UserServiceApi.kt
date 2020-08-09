package com.example.cartrack.data.network.user

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserServiceApi {

    @GET("/users")
    fun getUsers(): Single<Response<List<UserFromApi>>>
}