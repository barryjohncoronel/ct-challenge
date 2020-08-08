package com.example.cartrack.data.network

import io.reactivex.Single
import retrofit2.Retrofit

interface ApiFactory {

    /**
     * This will be used for API calls
     * */
    fun <T> api(apiClass: Class<T>): Single<T>

}