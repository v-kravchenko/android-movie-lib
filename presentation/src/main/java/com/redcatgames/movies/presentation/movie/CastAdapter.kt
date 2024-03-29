package com.redcatgames.movies.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.domain.model.MovieCast
import com.redcatgames.movies.presentation.ProfileSize
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.LayoutCastCrewBinding
import com.redcatgames.movies.presentation.getProfileUri
import com.redcatgmes.movies.baseui.util.loadByUrl

class CastAdapter : ListAdapter<MovieCast, CastHolder>(CastDiffCallback()) {

    var onItemClick: ((MovieCast) -> Unit)? = null

    fun setItems(list: List<MovieCast>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val binding: LayoutCastCrewBinding =
            LayoutCastCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(castHolder: CastHolder, position: Int) =
        castHolder.bind(getItem(position))
}

class CastHolder(
    private val itemBinding: LayoutCastCrewBinding,
    private val eventClickItem: ((MovieCast) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: MovieCast) {
        this.itemView.setOnClickListener { eventClickItem?.invoke(item) }
        itemBinding.textTitle.text = item.name
        itemBinding.textSubtitle.text = item.character
        itemBinding.castImage.loadByUrl(item.getProfileUri(ProfileSize.MEDIUM)) {
            val resId =
                if (item.gender == 2) R.drawable.person_placeholder_medium_male
                else R.drawable.person_placeholder_medium_female

            placeholder(resId)
            error(resId)
        }
    }
}

private class CastDiffCallback : DiffUtil.ItemCallback<MovieCast>() {
    override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem.personId == newItem.personId
    }

    override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
        return oldItem == newItem
    }
}
