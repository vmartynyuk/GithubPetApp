package ua.vmartyniuk.githubpetapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import ua.vmartyniuk.githubpetapp.domain.interactors.RepositoryInteractor
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repositoryInteractor: RepositoryInteractor
): ViewModel() {

    val repositories = repositoryInteractor.repositories
        .onEach { _isLoading.value = false }

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        addStabData()
    }

    private fun addStabData() {
        viewModelScope.launch {
            _isLoading.value = true
            repositoryInteractor.loadGithubRepositories()
        }
    }
}