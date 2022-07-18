package ua.vmartyniuk.githubpetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class RepositoryOwnerResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)