package com.ammase.androidmvvmdaggerrx.api


import com.ammase.androidmvvmdaggerrx.model.SignResponseModel
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Content-Type: application/json")
    @POST("api/v1/login")
    fun postLogin(
        @Body data: JsonObject
    ): Single<SignResponseModel>

}