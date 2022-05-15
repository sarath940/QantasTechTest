package com.example.qantastechtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.qantastechtest.data.model.QantasDataModel
import com.example.qantastechtest.data.model.typeconvertors.QantasRoomTypeConverter

@Database(entities = [QantasDataModel.QantasDataModelItem::class], version = 1)
@TypeConverters(QantasRoomTypeConverter::class)
abstract class QantasDatabase: RoomDatabase() {
    abstract fun qantasDao(): QantasDao

    companion object{
        const val DATABASE_NAME: String = "qantas_db"
    }
}