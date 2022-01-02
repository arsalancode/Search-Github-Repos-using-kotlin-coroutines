package com.github.repos.home.uimodels

import android.text.Spanned
import android.text.SpannedString
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.github.repos.home.R
import com.github.repos.core.lists.DataBoundModel
import com.github.repos.networking.networking.repo.model.RepoModel

class RepoUiModel(repoModel: RepoModel, private val onClick: (() -> Unit)?) :
    DataBoundModel(R.layout.repo_row) {

    init {
        Log.i("RepoUiModel", repoModel.toString())
    }

    val ownerAvatarUrl = MutableLiveData<String>().apply { postValue(repoModel.owner.ownerAvatar) }
    val ownerName = MutableLiveData<String>().apply { postValue(repoModel.owner.ownerName) }
    val repoName = MutableLiveData<String>().apply { postValue(repoModel.repoName) }
    val repoTitle = MutableLiveData<String>().apply { postValue(repoModel.repoTitle) }
    val repoDesc = MutableLiveData<String>().apply { postValue(repoModel.repoDesc) }
    val repoUrl = MutableLiveData<String>().apply { postValue(repoModel.repoUrl) }

    fun onClick(clickedView: View) {
        onClick?.invoke()
    }

}
