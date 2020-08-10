package com.example.cartrack.data.network.user

import com.example.cartrack.data.model.UserFromApi
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserServiceApi {

    @GET("users")
    fun getUsers(): Single<Response<List<UserFromApi>>>
}