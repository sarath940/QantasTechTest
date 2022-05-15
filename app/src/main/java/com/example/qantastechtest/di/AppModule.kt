package com.example.qantastechtest.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}