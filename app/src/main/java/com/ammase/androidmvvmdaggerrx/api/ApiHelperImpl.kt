package com.ammase.androidmvvmdaggerrx.api

import com.ammase.androidmvvmdaggerrx.model.SignResponseModel
import com.google.gson.JsonObject
import io.reactivex.Single
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) :
    ApiHelper {

    override fun PostLogin(data: JsonObject): Single<SignResponseModel> {
        return apiService.postLogin(data)
    }


}