package ua.vmartyniuk.githubpetapp.domain.repositories

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

    suspend fun loadGithubRepositories(filter: RepositoryFilter) {
        apiService.getRepositories(
            filter.query,
            filter.sortBy.value,
            filter.order.value,
            filter.page
        ).let { result ->
            result.map { responseModel -> responseModel.asModelList }
        }.let { result -> _repositories.tryEmit(result) }
    }
}