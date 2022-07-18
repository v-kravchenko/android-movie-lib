package com.redcatgames.movies.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.domain.model.MovieCast
import com.redcatgames.movies.presentation.databinding.LayoutCastBinding
import com.redcatgmes.movies.baseui.util.loadByUrl

class CastAdapter : ListAdapter<MovieCast, Holder>(ItemDiffCallback()) {

    var onItemClick: ((MovieCast) -> Unit)? = null

    fun setItems(list: List<MovieCast>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: LayoutCastBinding =
            LayoutCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))
}

class Holder(
    private val itemBinding: LayoutCastBinding,
    private val eventClickItem: ((MovieCast) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: MovieCast) {
        this.itemView.setOnClickListener { eventClickItem?.invoke(item) }
        itemBinding.textTitle.text = item.name
        itemBinding.castImage.loadByUrl("w92${item.profilePath}")
            //placeholder(R.drawable.poster_placeholder_w342)
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<MovieCast>() {
    override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem == newItem
    }
}
