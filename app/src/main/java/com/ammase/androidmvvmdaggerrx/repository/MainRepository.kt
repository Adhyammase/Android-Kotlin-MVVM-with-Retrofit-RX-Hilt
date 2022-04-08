package com.ammase.androidmvvmdaggerrx.repository

import com.ammase.androidmvvmdaggerrx.api.ApiHelper
import com.ammase.androidmvvmdaggerrx.model.SignResponseModel
import com.google.gson.JsonObject
import io.reactivex.Single
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    fun postLogin(data: JsonObject): Single<SignResponseModel> {
        return apiHelper.PostLogin(data)
    }

}