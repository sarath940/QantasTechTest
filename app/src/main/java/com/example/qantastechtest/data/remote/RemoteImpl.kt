package com.example.qantastechtest.data.remote

import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.remote.api.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class RemoteImpl @Inject constructor(private val apiInterface: ApiInterface) : RemoteSource {

    override suspend fun fetchAirData(): Response<QantasDataModel>{
        return apiInterface.getQantasDataModel()
    }
}