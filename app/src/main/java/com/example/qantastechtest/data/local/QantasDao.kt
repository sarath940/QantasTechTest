package com.example.qantastechtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.qantastechtest.data.model.QantasDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface QantasDao {
    @Query("select * from qantas")
    fun fetchQantasAirportList(): Flow<List<QantasDataModel.QantasDataModelItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(qantasItem: List<QantasDataModel.QantasDataModelItem>)
}
