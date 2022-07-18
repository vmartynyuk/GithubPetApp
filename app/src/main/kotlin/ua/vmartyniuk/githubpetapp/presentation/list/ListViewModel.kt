package ua.vmartyniuk.githubpetapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ua.vmartyniuk.githubpetapp.domain.interactors.RepositoryInteractor
import ua.vmartyniuk.githubpetapp.domain.models.OrderBy
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryFilter
import ua.vmartyniuk.githubpetapp.domain.models.SortBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repositoryInteractor: RepositoryInteractor
): ViewModel() {

    private val formatter: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    }

    val repositories = repositoryInteractor.repositories
        .onEach { _isLoading.value = false }

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val filter = MutableStateFlow<RepositoryFilter?>(null)

    init {
        observeFilterChanges()
        initFilter()
    }

    private fun observeFilterChanges() {
        viewModelScope.launch {
            filter.filterNotNull().collect { filter ->
                loadNewData(filter)
            }
        }
    }

    private fun initFilter() {
        val initialFilter = RepositoryFilter(
            buildLast30DaysQuery(),
            SortBy.STARS,
            OrderBy.DESC,
            INITIAL_PAGE
        )
        filter.value = initialFilter
    }

    private fun buildLast30DaysQuery(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -30)
        val formattedDate = formatter.format(calendar.time)
        return "created:>$formattedDate"
    }

    private suspend fun loadNewData(filter: RepositoryFilter) {
        _isLoading.value = true
        repositoryInteractor.loadGithubRepositories(filter)
    }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}