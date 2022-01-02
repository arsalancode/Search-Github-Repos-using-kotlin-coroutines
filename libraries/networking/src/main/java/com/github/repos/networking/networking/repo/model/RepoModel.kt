package com.github.repos.networking.networking.repo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoModel(

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("name")
    val repoName: String,

    @SerializedName("full_name")
    val repoTitle: String,

    @SerializedName("description")
    val repoDesc: String,

    @SerializedName("html_url")
    val repoUrl: String,

) : Parcelable {
    override fun toString(): String {
        return "RepoModel(owner=$owner, repoName='$repoName', repoTitle='$repoTitle', repoDesc='$repoDesc', repoUrl='$repoUrl')"
    }
}


@Parcelize
data class Owner (

    @SerializedName("avatar_url")
    val ownerAvatar: String,

    @SerializedName("login")
    val ownerName: String

) : Parcelable {
    override fun toString(): String {
        return "Owner(ownerAvatar='$ownerAvatar', ownerName='$ownerName')"
    }
}
