package com.extremeprogramming.financetracker.backEndConnection

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val httpClient = OkHttpClient.Builder().also {
        it.addInterceptor {
            val original = it.request()

            val request = original.newBuilder()
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyaXNvbTI5MkBnbWFpbC5jb20iLCJleHAiOjE1OTI1ODc3OTd9.Dp15H9hNANPRn2HWn3lRGAvY6LlvK-rPH95-b1sZIJb-kzdlUbfDHkM9uAXIwQNh7O63Ar-0Zr3aHu4reyTjZA")
                .method(original.method(), original.body())
                .build();
            it.proceed(request)
        }
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.104:8084")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}