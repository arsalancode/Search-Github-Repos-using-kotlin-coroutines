package com.github.repos.base

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.github.repos.navigation.result.Result
import com.github.repos.networking.domain.InternetHelper
import com.github.repos.networking.ui.NoConnectionFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    @Inject
    lateinit var connectivityManager : ConnectivityManager

    @Inject
    lateinit var internetHelper : InternetHelper

    private var baseResultData: Intent? = null

    /**
     * This is called for every result added to the activity to be consumed.
     * @return true if the result has been consumed (and will then not be propagated to other activities) or false if not.
     */
    open fun useReturnData(resultData: Result<Parcelable>): Boolean {
        return false
    }

    val noConnectionFragment: ((Boolean) -> NoConnectionFragment) = { hasPoorConnection ->
        NoConnectionFragment.newInstance(hasPoorConnection)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == Result.REQUEST_CODE) {
            val data = resultData ?: Intent()
            data.extras?.keySet()?.forEach {
                val result = data.extras?.get(it)
                if (result is Result<out Parcelable>) {
                    if (useReturnData(result)) {
                        data.extras?.remove(it)
                    }
                }
            }
            this.baseResultData = data
        }
    }

    override fun startActivity(intent: Intent?) {
        super.startActivityForResult(intent, Result.REQUEST_CODE)
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivityForResult(intent, Result.REQUEST_CODE, options)
    }
}