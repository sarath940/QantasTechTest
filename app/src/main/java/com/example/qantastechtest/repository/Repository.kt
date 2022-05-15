package com.example.qantastechtest.repository


import com.example.qantastechtest.data.Resource
import com.example.qantastechtest.data.model.QantasDataModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchQantasModelData(): Flow<Resource<QantasDataModel>>
}