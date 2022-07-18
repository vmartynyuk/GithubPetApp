package ua.vmartyniuk.githubpetapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.vmartyniuk.githubpetapp.data.network.model.RepositoriesListResponse

@Parcelize
data class RepositoryModel(
    val id: Long,
    val title: String,
    val description: String?,
    val ownerName: String,
    val ownerAvatar: String,
    val starsCount: Int,
    val detailsUrl: String
): Parcelable

val RepositoriesListResponse.asModelList: List<RepositoryModel>
    get() = this.items.map { response ->
        with(response) {
            RepositoryModel(
                id,
                name,
                description,
                owner.login,
                owner.avatarUrl,
                stargazersCount,
                htmlUrl
            )
        }
    }