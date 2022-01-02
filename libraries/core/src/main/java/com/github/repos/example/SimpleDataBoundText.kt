package com.github.repos.example

import androidx.lifecycle.MutableLiveData
import com.github.repos.core.R
import com.github.repos.core.lists.DataBoundModel

class SimpleDataBoundText : DataBoundModel(R.layout.data_bound_text) {
    val text = MutableLiveData<String>()
}