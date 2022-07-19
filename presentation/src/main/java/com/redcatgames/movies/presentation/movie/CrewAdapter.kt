package com.redcatgames.movies.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.domain.model.MovieCrew
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.LayoutCastCrewBinding
import com.redcatgmes.movies.baseui.util.loadByUrl

class CrewAdapter : ListAdapter<MovieCrew, CrewHolder>(CrewDiffCallback()) {

    var onItemClick: ((MovieCrew) -> Unit)? = null

    fun setItems(list: List<MovieCrew>) {
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewHolder {
        val binding: LayoutCastCrewBinding =
            LayoutCastCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(CrewHolder: CrewHolder, position: Int) = CrewHolder.bind(getItem(position))
}

class CrewHolder(
    private val itemBinding: LayoutCastCrewBinding,
    private val eventClickItem: ((MovieCrew) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: MovieCrew) {
        this.itemView.setOnClickListener { eventClickItem?.invoke(item) }
        itemBinding.textTitle.text = item.name
        itemBinding.textSubtitle.text = item.job
        itemBinding.castImage.loadByUrl("w154${item.profilePath}") {
            val resId = if (item.gender == 2) R.drawable.person_placeholder_w154_male
            else R.drawable.person_placeholder_w154_female

            placeholder(resId)
            error(resId)
        }
    }
}

private class CrewDiffCallback : DiffUtil.ItemCallback<MovieCrew>() {
    override fun areItemsTheSame(oldItem: MovieCrew, newItem: MovieCrew): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieCrew, newItem: MovieCrew): Boolean {
        return oldItem == newItem
    }
}
