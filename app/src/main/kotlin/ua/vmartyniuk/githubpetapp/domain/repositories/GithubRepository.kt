package ua.vmartyniuk.githubpetapp.domain.repositories

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor() {

    private val _repositories = MutableSharedFlow<Result<List<RepositoryModel>>>(replay = 1)
    val repositories: Flow<Result<List<RepositoryModel>>> = _repositories.asSharedFlow()

    suspend fun loadGithubRepositories() {
        delay(2000)
        _repositories.tryEmit(Result.success(stubData))
    }

    companion object {
        private val stubData = listOf(
            RepositoryModel(
                id = 401572203,
                title = "BorrowScript",
                description = "TypeScript with a Borrow Checker. Multi-threaded, Tiny binaries. No GC. Easy to write.",
                ownerName = "alshdavid",
                ownerAvatar = "https://avatars.githubusercontent.com/u/12656294?v=4",
                starsCount = 1353
            ),
            RepositoryModel(
                id = 380199932,
                title = "covidpass",
                description = "Web app for adding EU Digital COVID Certificates to your wallet apps",
                ownerName = "covidpass-org",
                ownerAvatar = "https://avatars.githubusercontent.com/u/86500499?v=4",
                starsCount = 1227
            ),
            RepositoryModel(
                id = 470985349,
                title = "startpack",
                description = "Selfhosted tech starter pack for development of new project or startup",
                ownerName = "tldr-devops",
                ownerAvatar = "https://avatars.githubusercontent.com/u/68926815?v=4",
                starsCount = 1353
            ),
            RepositoryModel(
                id = 376202591,
                title = "DIY-CNC-machine",
                description = "How to build your own CNC machine from scratch",
                ownerName = "maxvfischer",
                ownerAvatar = "https://avatars.githubusercontent.com/u/28877367?v=4",
                starsCount = 1185
            ),
        )
    }
}