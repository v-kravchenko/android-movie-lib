package com.redcatgames.movies.presentation.popular

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.databinding.RowMovieBinding
import com.redcatgames.movies.domain.model.Movie
import com.redcatgames.movies.presentation.util.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter : ListAdapter<Movie, MovieAdapter.VH>(MovieDiffCallback()) {

    private var list = listOf<Movie>()
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
    var eventClickItem = SingleLiveEvent<Movie>()

    fun setItems(list: List<Movie>) {
        this.list = list
        submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: RowMovieBinding =
            RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding, dateFormat)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(
        private val itemBinding: RowMovieBinding,
        private val dateFormat: SimpleDateFormat
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var item: Movie

        init {
            itemBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: Movie) {
            this.item = item
            itemBinding.text1.text = "[${item.popularity}] ${item.title}"
        }

        override fun onClick(v: View?) {
            eventClickItem.postValue(item)
        }
    }
}