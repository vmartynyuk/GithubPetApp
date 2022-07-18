package ua.vmartyniuk.githubpetapp.data.network.model

import com.google.gson.annotations.SerializedName

data class RepositoriesListResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val items: List<RepositoryResponse>
)