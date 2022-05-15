package com.example.qantastechtest.di

import com.example.qantastechtest.data.local.LocalDataSource
import com.example.qantastechtest.data.local.LocalDataSourceImpl
import com.example.qantastechtest.data.remote.RemoteImpl
import com.example.qantastechtest.data.remote.RemoteSource
import com.example.qantastechtest.repository.QantasRepository
import com.example.qantastechtest.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun provideDataSource(localDataSource: LocalDataSourceImpl?): LocalDataSource?

    @Binds
    abstract fun provideRepoImpl(repo: QantasRepository?): Repository?

    @Binds
    abstract fun provideRemoteImpl(remote: RemoteImpl?): RemoteSource?
}