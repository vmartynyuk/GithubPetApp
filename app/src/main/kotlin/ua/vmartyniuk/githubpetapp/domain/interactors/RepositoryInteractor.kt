package ua.vmartyniuk.githubpetapp.domain.interactors

import kotlinx.coroutines.flow.Flow
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import ua.vmartyniuk.githubpetapp.domain.repositories.GithubRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryInteractor @Inject constructor(
    private val githubRepository: GithubRepository
) {

    val repositories: Flow<Result<List<RepositoryModel>>> = githubRepository.repositories

    suspend fun loadGithubRepositories() {
        githubRepository.loadGithubRepositories()
    }
}