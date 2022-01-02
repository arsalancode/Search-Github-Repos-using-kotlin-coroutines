package com.github.repos.networking.provider

import android.content.Context
import com.github.repos.networking.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    val githubInstance by lazy {
        provideRetrofit(
            BuildConfig.GITHUB_LIVE_HOST
        )
    }

    private fun provideRetrofit(
        host: String,
        okHttpClient: OkHttpClient = provideOkHttpClient(),
        converterFactory: Converter.Factory = GsonConverterFactory.create()
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

}