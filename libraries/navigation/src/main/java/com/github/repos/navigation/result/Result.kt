package com.github.repos.navigation.result

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Result<out T : Parcelable>(val key: String, val data: T) : Parcelable {

    companion object {
        const val REQUEST_CODE = 666
    }

}
