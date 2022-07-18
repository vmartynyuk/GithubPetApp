package ua.vmartyniuk.githubpetapp.domain.models

data class RepositoryFilter(
    val query: String,
    val sortBy: SortBy,
    val order: OrderBy
)

enum class SortBy(val value: String) {
    STARS("stars"), FOLLOWERS("followers")
}

enum class OrderBy(val value: String) {
    DESC("desc"), ASC("ask")
}