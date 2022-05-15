package com.example.qantastechtest.data.local
import androidx.lifecycle.LiveData
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val qantasDao: QantasDao) : LocalDataSource {
    override suspend fun saveData(list: List<QantasDataModelItem>) {
        list.forEach {
            qantasDao.insertQantasDataModelItem(it)
        }
    }

    override suspend fun deleteData() {
       qantasDao.delete()
    }

    override fun getAirData(): LiveData<List<QantasDataModelItem>> {
       return qantasDao.fetchQantasAirportList()
    }
}