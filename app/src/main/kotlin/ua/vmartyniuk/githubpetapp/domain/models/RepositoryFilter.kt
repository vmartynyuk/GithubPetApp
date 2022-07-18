package ua.vmartyniuk.githubpetapp.domain.models

data class RepositoryFilter(
    val queries: List<String>,
    val sortBy: SortBy,
    val order: OrderBy,
    val page: Int
) {
    val query: String
        get() = queries.joinToString(" ")
}

enum class SortBy(val value: String) {
    STARS("stars"), FOLLOWERS("followers")
}

enum class OrderBy(val value: String) {
    DESC("desc"), ASC("ask")
}