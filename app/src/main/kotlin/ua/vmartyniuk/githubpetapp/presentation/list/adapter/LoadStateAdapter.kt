package ua.vmartyniuk.githubpetapp.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ua.vmartyniuk.githubpetapp.databinding.LoaderItemBinding

class LoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LoaderItemBinding.inflate(layoutInflater, parent, false)
        return LoadStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        return holder.bind(loadState)
    }
}

class LoadStateViewHolder(
    binding: LoaderItemBinding,
    retry: () -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private val loader = binding.loader
    private val retryButton = binding.retryButton.also {
        it.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        loader.isVisible = loadState is LoadState.Loading
        retryButton.isVisible = loadState is LoadState.Error
    }
}