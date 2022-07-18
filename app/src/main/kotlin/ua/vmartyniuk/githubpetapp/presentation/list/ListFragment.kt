package ua.vmartyniuk.githubpetapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.vmartyniuk.githubpetapp.R
import ua.vmartyniuk.githubpetapp.databinding.FragmentListBinding
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import ua.vmartyniuk.githubpetapp.presentation.list.adapter.LoadStateAdapter
import ua.vmartyniuk.githubpetapp.presentation.list.adapter.RepositoryAdapter
import ua.vmartyniuk.githubpetapp.presentation.utils.launchWithRepeatOnStarted

@AndroidEntryPoint
class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RepositoryAdapter(onItemClicked)
        setupList(adapter)
        binding.retryButton.setOnClickListener {
            adapter.retry()
        }

        launchWithRepeatOnStarted {
            launch { observeAdapterStates(adapter) }
            launch { observeRepositoriesList(adapter) }
        }
    }

    private fun setupList(adapter: RepositoryAdapter) {
        binding.repositoryList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter.withLoadStateFooter(
                footer = LoadStateAdapter { adapter.retry() }
            )
        }
    }

    private suspend fun observeAdapterStates(adapter: RepositoryAdapter) {
        adapter.loadStateFlow.collectLatest { loadStates ->
            val state = loadStates.refresh
            val isLoading = state is LoadState.Loading && adapter.itemCount == 0
            val isEmpty = state is LoadState.NotLoading && state.endOfPaginationReached && adapter.itemCount == 0
            val isError = state is LoadState.Error && adapter.itemCount == 0

            with(binding) {
                repositoryList.isVisible = !isLoading && !isError
                loader.isVisible = isLoading
                retryButton.isVisible = isError
                placeholder.apply {
                    isVisible = isEmpty || isError
                    text = when {
                        isEmpty -> getString(R.string.no_data_text)
                        isError -> (state as LoadState.Error).error.message
                        else -> null
                    }
                }
            }
        }
    }

    private suspend fun observeRepositoriesList(adapter: RepositoryAdapter) {
        viewModel.repositories.collect { loadState ->
            adapter.submitData(loadState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val onItemClicked = { model: RepositoryModel ->
        // todo: open details
    }
}