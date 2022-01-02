package com.github.repos.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor () : ViewModel() {
    var launchHomeScreen: MutableLiveData<Boolean> = MutableLiveData()

    private val splashTime = 2000L // 2 seconds

    init {

        viewModelScope.launch {

            // Notify UI after this time
            delay(splashTime)

            launchHomeScreen.postValue(true)
        }

    }

}