package ua.vmartyniuk.githubpetapp.data.network.service

import retrofit2.http.GET
import retrofit2.http.Query
import ua.vmartyniuk.githubpetapp.data.network.model.RepositoriesListResponse

interface ApiService {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int
    ): Result<RepositoriesListResponse>

}