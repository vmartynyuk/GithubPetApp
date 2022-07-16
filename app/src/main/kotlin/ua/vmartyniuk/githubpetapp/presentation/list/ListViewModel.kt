package ua.vmartyniuk.githubpetapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(): ViewModel() {

    val repositories = MutableStateFlow<Result<List<RepositoryModel>>>(Result.success(emptyList()))

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        addStabData()
    }

    private fun addStabData() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(2000)
            repositories.value = Result.success(stubData)
            _isLoading.value = false
        }
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