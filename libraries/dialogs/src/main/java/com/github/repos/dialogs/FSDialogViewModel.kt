package com.github.repos.dialogs

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FSDialogViewModel(
    @DrawableRes imageRes: Int?,
    @StringRes titleRes: Int?,
    @StringRes textRes: Int?,
    @RawRes lottieRes: Int?,
    textString: String?,
    @StringRes negativeButtonTextRes: Int?,
    @StringRes positiveButtonTextRes: Int?
) : ViewModel() {

    val image = MutableLiveData<Int>().apply { value = imageRes }
    val title = MutableLiveData<Int>().apply { value = titleRes }
    val text = MutableLiveData<Int>().apply { value = textRes }
    val lottieId = MutableLiveData<Int>().apply { value = lottieRes }
    val stringText = MutableLiveData<String>().apply { value = textString }


    val negativeButtonText = MutableLiveData<Int>().apply { value = negativeButtonTextRes }
    val positiveButtonText = MutableLiveData<Int>().apply { value = positiveButtonTextRes }

    fun setImageRes(@DrawableRes imageRes: Int?) {
        image.value = imageRes
    }


    fun setTitleRes(@StringRes titleRes: Int?) {
        title.value = titleRes
    }

    fun setTextRes(@StringRes textRes: Int?) {
        text.value = textRes
    }

    fun setNegativeButtonTextRes(@StringRes negativeButtonTextRes: Int?) {
        negativeButtonText.value = negativeButtonTextRes
    }

    fun setPositiveButtonTextRes(@StringRes positiveButtonTextRes: Int?) {
        positiveButtonText.value = positiveButtonTextRes
    }

    fun setLottieImage(@RawRes lottieRes: Int?) {
        lottieId.value = lottieRes
    }

    fun setText(text: String?) {
        stringText.value = text
    }


}
