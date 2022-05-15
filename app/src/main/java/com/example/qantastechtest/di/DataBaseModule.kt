package com.example.qantastechtest.di

import android.content.Context
import androidx.room.Room
import com.example.qantastechtest.data.local.QantasDao
import com.example.qantastechtest.data.local.QantasDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): QantasDatabase {
        return Room
            .databaseBuilder(
                context,
                QantasDatabase::class.java,
                QantasDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDAO(database: QantasDatabase): QantasDao {
        return database.qantasDao()
    }
}