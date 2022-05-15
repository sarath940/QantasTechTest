package com.example.qantastechtest.repository


import androidx.lifecycle.LiveData
import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import retrofit2.Response

interface Repository {
    suspend fun fetchRemoteData(): Response<QantasDataModel>
    suspend fun saveData(listItem: List<QantasDataModelItem>)
    fun fetchLocalData(): LiveData<List<QantasDataModelItem>>
}