package com.example.qantastechtest.data.local

import androidx.lifecycle.LiveData
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem


interface LocalDataSource {
    suspend fun saveData(list: List<QantasDataModelItem>)
    suspend fun deleteData()
    fun getAirData(): LiveData<List<QantasDataModelItem>>
}
