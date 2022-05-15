package com.example.qantastechtest.repository

import androidx.lifecycle.LiveData
import com.example.qantastechtest.data.local.LocalDataSource
import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.remote.RemoteSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class QantasRepository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val localDataSource: LocalDataSource,
) : Repository {
    override suspend fun fetchRemoteData(): Response<QantasDataModel> {
        return remoteSource.fetchAirData()
    }

    override suspend fun saveData(list: List<QantasDataModel.QantasDataModelItem>) {
        localDataSource.deleteData()
        localDataSource.saveData(list)
    }


    override fun fetchLocalData(): LiveData<List<QantasDataModel.QantasDataModelItem>> {
        return localDataSource.getAirData()
    }

}