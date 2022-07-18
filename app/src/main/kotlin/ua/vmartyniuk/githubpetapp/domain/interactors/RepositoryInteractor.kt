package ua.vmartyniuk.githubpetapp.domain.interactors

import ua.vmartyniuk.githubpetapp.domain.models.RepositoryFilter
import ua.vmartyniuk.githubpetapp.domain.repositories.GithubRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryInteractor @Inject constructor(
    private val githubRepository: GithubRepository
) {

    fun loadGithubRepositories(filter: RepositoryFilter) =
        githubRepository.loadGithubRepositories(filter)
}