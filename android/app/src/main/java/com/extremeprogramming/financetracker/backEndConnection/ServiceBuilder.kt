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
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiQGIiLCJleHAiOjE1OTI1NzgwMDN9.V5Cr5QpCT_M-pcX1LXQOeZ3EWncQLp87GPi4XS3yFEGKxA3vWKz6ap4uW8tpDcYPVqxY4fAX5hhYPn_yugv7sw")
                .method(original.method(), original.body())
                .build();
            it.proceed(request)
        }
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.169.3:8084")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}