package com.example.qantastechtest.data.remote

import com.example.qantastechtest.data.model.QantasDataModel
import retrofit2.Response


interface RemoteSource {
    suspend fun fetchAirData(): Response<QantasDataModel>
}