package com.redcatgames.movies.presentation.popular

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.databinding.RowMovieBinding
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.util.format

class MovieAdapter : ListAdapter<Movie, Holder>(ItemDiffCallback()) {

    var onItemClick: ((Movie) -> Unit)? = null

    fun setItems(list: List<Movie>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: RowMovieBinding =
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))
}

class Holder(
    private val itemBinding: RowMovieBinding,
    private val eventClickItem: ((Movie) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: Movie) {
        this.itemView.setOnClickListener { eventClickItem?.invoke(item) }
        itemBinding.text1.text = item.title
        itemBinding.text2.text = item.voteAverage.format(1)
        itemBinding.text3.text = item.popularity.toString()
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
