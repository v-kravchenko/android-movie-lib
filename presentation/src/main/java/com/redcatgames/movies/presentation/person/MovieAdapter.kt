package com.redcatgames.movies.presentation.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.domain.model.PersonCast
import com.redcatgames.movies.presentation.PosterSize
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.LayoutMovieHorizontalBinding
import com.redcatgames.movies.presentation.getPosterUri
import com.redcatgmes.movies.baseui.util.loadByUrl

class MovieAdapter : ListAdapter<PersonCast, Holder>(ItemDiffCallback()) {

    var onItemClick: ((PersonCast) -> Unit)? = null

    fun setItems(list: List<PersonCast>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: LayoutMovieHorizontalBinding =
            LayoutMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))
}

class Holder(
    private val itemBinding: LayoutMovieHorizontalBinding,
    private val eventClickItem: ((PersonCast) -> Unit)?,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: PersonCast) {
        this.itemView.setOnClickListener { eventClickItem?.invoke(item) }
        itemBinding.textTitle.text = item.title
        itemBinding.textRating.text = item.voteRating
        itemBinding.posterImage.loadByUrl(item.getPosterUri(PosterSize.MEDIUM)) {
            placeholder(R.drawable.poster_placeholder_medium)
            error(R.drawable.poster_placeholder_medium)
        }
    }
}

private class ItemDiffCallback : DiffUtil.ItemCallback<PersonCast>() {
    override fun areItemsTheSame(oldItem: PersonCast, newItem: PersonCast): Boolean {
        return oldItem.creditId == newItem.creditId
    }

    override fun areContentsTheSame(oldItem: PersonCast, newItem: PersonCast): Boolean {
        return oldItem == newItem
    }
}
