package com.example.cartrack.util.internet

import io.reactivex.Single

interface InternetUtil {

    fun isNetworkAvailable() : Single<Boolean>
}