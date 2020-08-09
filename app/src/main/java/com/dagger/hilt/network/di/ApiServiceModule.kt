package com.dagger.hilt.network.di

import com.dagger.hilt.BuildConfig
import com.dagger.hilt.network.ApiService
import com.dagger.hilt.network.ServerUrl
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiServiceModule {

    private const val REQUEST_TIME_OUT = 120L //Seconds

    @Singleton
    @Provides
    fun apiService(
        retrofit: Retrofit
    ) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client : Lazy<OkHttpClient>,
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit {

        return Retrofit.Builder()
            .callFactory(client.get())
            .baseUrl(ServerUrl.API_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {

        val httpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        httpClientBuilder.readTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)
        httpClientBuilder.connectTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

    @Provides
    fun provideGsonConverter() : GsonConverterFactory = GsonConverterFactory.create()

}