package ua.vmartyniuk.githubpetapp.domain.models

data class RepositoryModel(
    val id: Long,
    val title: String,
    val description: String,
    val ownerName: String,
    val ownerAvatar: String,
    val starsCount: Int
)