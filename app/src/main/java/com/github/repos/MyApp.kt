package com.github.repos

import android.app.Application
import com.feliperrm.utils.init
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

}