package com.example.qantastechtest.data.remote.api

import com.example.qantastechtest.data.model.QantasDataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("flight/refData/airport")
    suspend fun getQantasDataModel(): Response<QantasDataModel>
}
