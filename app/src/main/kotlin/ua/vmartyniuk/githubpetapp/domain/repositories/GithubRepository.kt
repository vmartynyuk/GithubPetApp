package ua.vmartyniuk.githubpetapp.domain.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ua.vmartyniuk.githubpetapp.data.network.service.ApiService
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryFilter
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import ua.vmartyniuk.githubpetapp.domain.models.asModelList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val apiService: ApiService
) {

    private val _repositories = MutableSharedFlow<Result<List<RepositoryModel>>>(replay = 1)
    val repositories: Flow<Result<List<RepositoryModel>>> = _repositories.asSharedFlow()

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