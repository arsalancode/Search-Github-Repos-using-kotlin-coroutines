package com.github.repos.base.di

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.app.ActivityCompat
import com.github.repos.networking.domain.InternetHelper
import com.github.repos.networking.networking.repo.RepoService
import com.github.repos.networking.networking.repo.SearchRepository
import com.github.repos.networking.networking.repo.SearchRepositoryImpl
import com.github.repos.networking.provider.RetrofitBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NetworkingAbstractModule {

    @Binds
    abstract fun provideSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

}