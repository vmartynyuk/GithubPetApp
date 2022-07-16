package ua.vmartyniuk.githubpetapp.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.vmartyniuk.githubpetapp.R
import ua.vmartyniuk.githubpetapp.databinding.ListItemBinding
import ua.vmartyniuk.githubpetapp.domain.models.RepositoryModel

class RepositoryAdapter(private val onItemClick: (RepositoryModel) -> Unit): ListAdapter<RepositoryModel, RepositoryViewHolder>(RepositoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)
        return RepositoryViewHolder(binding) { position ->
            if (position != RecyclerView.NO_POSITION) {
                onItemClick(getItem(position))
            }
        }
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bind(getItem(position))
        }
    }

    override fun onViewRecycled(holder: RepositoryViewHolder) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }
}

class RepositoryViewHolder(
    private val binding: ListItemBinding,
    onItemClick: (Int) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    fun bind(item: RepositoryModel) {
        with(binding) {
            repositoryTitle.text = item.title
            repositoryDescription.text = item.description
            repositoryOwner.text = itemView.context.getString(R.string.by_owner, item.ownerName)
            repositoryStars.text = item.starsCount.toString()

            Glide.with(itemView.context)
                .load(item.ownerAvatar)
                .into(avatar)
        }
    }

    fun onViewRecycled() {
        Glide.with(itemView.context)
            .clear(binding.avatar)
    }
}

class RepositoryDiffCallback: DiffUtil.ItemCallback<RepositoryModel>() {
    override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel) =
        oldItem == newItem

}