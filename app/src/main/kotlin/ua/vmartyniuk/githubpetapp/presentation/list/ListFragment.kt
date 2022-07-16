package ua.vmartyniuk.githubpetapp.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ua.vmartyniuk.githubpetapp.databinding.FragmentListBinding
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel
import ua.vmartyniuk.githubpetapp.presentation.list.adapter.RepositoryAdapter

class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

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
    }

    private fun setupList(adapter: RepositoryAdapter) {
        binding.repositoryList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
        adapter.submitList(stubData)
    }

    private val onItemClicked = { model: RepositoryModel ->
        // todo: open details
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