package com.example.ct.data.network

import io.reactivex.Single

interface ApiFactory {

    /**
     * This will be used for API calls
     * */
    fun <T> api(apiClass: Class<T>): Single<T>

}