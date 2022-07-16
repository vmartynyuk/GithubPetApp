package ua.vmartyniuk.githubpetapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ua.vmartyniuk.githubpetapp.databinding.FragmentListBinding
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import ua.vmartyniuk.githubpetapp.presentation.list.adapter.RepositoryAdapter
import ua.vmartyniuk.githubpetapp.presentation.utils.launchWithRepeatOnStarted

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

        launchWithRepeatOnStarted {
            launch { observeLoadingState() }
            launch { observeRepositoriesList(adapter) }
        }
    }

    private fun setupList(adapter: RepositoryAdapter) {
        binding.repositoryList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    private suspend fun observeLoadingState() {
        viewModel.isLoading.collect { isLoading ->
            with(binding) {
                loader.isVisible = isLoading
                repositoryList.isGone = isLoading
            }
        }
    }

    private suspend fun observeRepositoriesList(adapter: RepositoryAdapter) {
        viewModel.repositories.collect { result ->
            result.onSuccess { list ->
                adapter.submitList(list)
            }.onFailure {
                // todo: show Error
            }
        }
    }

    private val onItemClicked = { model: RepositoryModel ->
        // todo: open details
    }
}