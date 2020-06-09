package com.extremeprogramming.financetracker.backEndConnection

import android.app.Activity
import android.content.Context
import com.extremeprogramming.financetracker.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private var httpClient = updateClient()
    private var retrofit = updateRetro()
    var key: String? = "";

    fun<T> buildService(service: Class<T>): T{
        updateClientAndRetro()
        return retrofit.create(service)
    }

    fun updateClientAndRetro(){
        httpClient = updateClient()
        retrofit = updateRetro()
    }
    fun updateClient() : OkHttpClient{

        val client = OkHttpClient.Builder().also {
            it.addInterceptor {
                val original = it.request()

                val request = original.newBuilder()
                    .header("Authorization", key)
                    .method(original.method(), original.body())
                    .build();
                it.proceed(request)
            }
        }.build()

        return client
    }
    fun updateRetro() : Retrofit{
        val retro = Retrofit.Builder()
            .baseUrl("http://192.168.1.104:8084")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        return retro
    }

}