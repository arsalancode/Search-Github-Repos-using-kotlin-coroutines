package com.github.repos.networking.domain

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.repos.core.utils.flow.asFlow
import com.github.repos.networking.networking.NoInternetException
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InternetHelper @Inject constructor(
    private val connectivityManager: ConnectivityManager
) {
    fun executeCheckInternetStatus(): Flow<Unit> = asFlow { checkInternetStatus() }

    private fun checkInternetStatus() {
        val isInternetAvailable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isInternetAvailableAboveAndroidM()
        } else {
            isInternetAvailableBelowAndroidM()
        }

        if (!isInternetAvailable) throw NoInternetException()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isInternetAvailableAboveAndroidM(): Boolean {
        val networkCapabilities = connectivityManager?.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    private fun isInternetAvailableBelowAndroidM(): Boolean {
        connectivityManager.run {
            connectivityManager?.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            } ?: return false
        }
    }
}