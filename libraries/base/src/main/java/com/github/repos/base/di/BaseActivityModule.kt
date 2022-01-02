package com.github.repos.base.di

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.github.repos.base.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object BaseActivityModule {

    @Provides
    fun provideBaseActivity(activity: Activity): BaseActivity {
        check(activity is BaseActivity) { "Every Activity is expected to extend BaseActivity" }
        return activity
    }

}