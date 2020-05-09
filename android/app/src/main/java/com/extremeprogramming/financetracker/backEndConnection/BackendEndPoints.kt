package com.extremeprogramming.financetracker.backEndConnection

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.http.*

interface BackendEndPoints {

    @POST("/login")
    fun PostLogin(@Body user: User) : retrofit2.Call<ResponseBody>

    @POST("/sign-up")
    fun SingUp(@Body user: User) : retrofit2.Call<ResponseBody>
}