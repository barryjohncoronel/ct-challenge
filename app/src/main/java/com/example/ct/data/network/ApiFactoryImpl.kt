package com.example.ct.data.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiFactoryImpl @Inject constructor() : ApiFactory {

    override fun <T> api(apiClass: Class<T>): Single<T> {
        return retrofit()
            .map { retrofit ->
                retrofit.create(apiClass)
            }
    }

    private fun retrofit(): Single<Retrofit> {
        return client()
            .flatMap(this::intoRetrofit)
    }

    private fun client(): Single<OkHttpClient> {
        return Single.fromCallable {
                OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
            }
            .flatMap(this::addLoggingInterceptor)
            .map(OkHttpClient.Builder::build)
    }

    private fun intoRetrofit(client: OkHttpClient): Single<Retrofit> {
        return Single.fromCallable {
                Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
        return Single.fromCallable {
            val loggingInterceptor = HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }

            builder.addInterceptor(loggingInterceptor)

            builder
        }
    }

}