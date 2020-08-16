package com.example.ct.util.internet

import io.reactivex.Single

interface InternetUtil {

    fun isNetworkAvailable() : Single<Boolean>
}