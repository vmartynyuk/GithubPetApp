package ua.vmartyniuk.githubpetapp.domain.models

import ua.vmartyniuk.githubpetapp.data.network.model.RepositoriesListResponse

data class RepositoryModel(
    val id: Long,
    val title: String,
    val description: String?,
    val ownerName: String,
    val ownerAvatar: String,
    val starsCount: Int
)

val RepositoriesListResponse.asModelList: List<RepositoryModel>
    get() = this.items.map { response ->
        with(response) {
            RepositoryModel(
                id,
                name,
                description,
                owner.login,
                owner.avatarUrl,
                stargazersCount
            )
        }
    }