package com.dagger.hilt.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dagger.hilt.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

}