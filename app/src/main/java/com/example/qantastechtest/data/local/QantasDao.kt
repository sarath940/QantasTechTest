package com.example.qantastechtest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qantastechtest.data.model.QantasDataModel.QantasDataModelItem

@Dao
interface QantasDao {
    @Query("select * from qantas")
    fun fetchQantasAirportList(): LiveData<List<QantasDataModelItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQantasDataModelItem(qantasItem: QantasDataModelItem)

    @Query("DELETE FROM qantas")
    suspend fun delete()
}
