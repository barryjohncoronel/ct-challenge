package com.example.ct

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.rules.ExternalResource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class MockWebServiceRule : ExternalResource() {

    private var gson: Gson? = null

    private var mockWebServer: MockWebServer

    private var retrofit: Retrofit

    init {
        gson = GsonBuilder().create()

        mockWebServer = MockWebServer()

        retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <T> create(clazz: Class<T>?): T {
        return retrofit.create(clazz)
    }

    fun <T> fromBodyOf(request: RecordedRequest, clazz: Class<T>?): T {
        return gson!!.fromJson(request.body.readUtf8(), clazz)
    }

    fun <T> fromBodyOf(request: RecordedRequest, type: Type?): T {
        return gson!!.fromJson(request.body.readUtf8(), type)
    }

    fun server(): MockWebServer {
        return mockWebServer
    }

    fun toBody(`object`: Any?): String {
        return gson!!.toJson(`object`)
    }
}