package com.redcatgames.movies.presentation.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.LayoutMovieBinding
import com.redcatgames.movies.util.format
import com.redcatgmes.movies.baseui.util.loadByUrl

class MovieAdapter : ListAdapter<Movie, Holder>(ItemDiffCallback()) {

    var onItemClick: ((Movie) -> Unit)? = null

    fun setItems(list: List<Movie>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: LayoutMovieBinding =
            LayoutMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))
}

class Holder(
    private val itemBinding: LayoutMovieBinding,
    private val eventClickItem: ((Movie) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: Movie) {
        this.itemView.setOnClickListener { eventClickItem?.invoke(item) }
        itemBinding.textTitle.text = item.title
        itemBinding.textRating.text =
            if (item.voteAverage > 0) item.voteAverage.format(1).replace(',', '.') else "n/a"
        itemBinding.posterImage.loadByUrl("w342${item.posterPath}") {
            placeholder(R.drawable.poster_placeholder_w342)
        }
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
