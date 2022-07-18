package ua.vmartyniuk.githubpetapp.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.vmartyniuk.githubpetapp.data.network.service.ApiService
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryFilter
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun loadGithubRepositories(filter: RepositoryFilter) : Flow<PagingData<RepositoryModel>>  {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { RepositoryPagingSource(apiService, filter) }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}