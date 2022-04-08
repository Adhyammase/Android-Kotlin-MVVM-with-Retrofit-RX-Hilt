package com.ammase.androidmvvmdaggerrx.api

import com.ammase.androidmvvmdaggerrx.model.SignResponseModel
import com.google.gson.JsonObject
import io.reactivex.Single

interface ApiHelper {
    fun PostLogin(data: JsonObject): Single<SignResponseModel>
}