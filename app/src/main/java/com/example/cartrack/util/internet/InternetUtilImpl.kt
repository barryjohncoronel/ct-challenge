package com.example.cartrack.util.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.cartrack.data.exceptions.NoInternetException
import io.reactivex.Single
import javax.inject.Inject

class InternetUtilImpl @Inject constructor(
    private val context: Context
) : InternetUtil {

    override fun isNetworkAvailable(): Single<Boolean> {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        val isNetworkAvailable = null != networkInfo &&
                (networkInfo.state == NetworkInfo.State.CONNECTED ||
                        networkInfo.state == NetworkInfo.State.CONNECTING)

        return if (isNetworkAvailable) {
            Single.fromCallable { true }
        } else {
            Single.error(NoInternetException())
        }
    }
}