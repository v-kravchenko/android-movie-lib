package com.redcatgames.movies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redcatgames.movies.presentation.base.BaseFragment
import com.redcatgames.movies.presentation.databinding.MovieFragmentBinding
import com.redcatgames.movies.presentation.util.autoCleared
import com.redcatgames.movies.presentation.util.loadByUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val args by navArgs<MovieFragmentArgs>()
    private val viewModel: MovieViewModel by viewModels()
    private var binding: MovieFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        setupObserver()
    }

    private fun setupObserver() {

        observe(viewModel.movieInfo) { info ->
            info?.let { movieInfo ->
                binding.topAppBar.title = movieInfo.movie.title
                binding.text2.text = movieInfo.movie.overview
                binding.posterImage.loadByUrl("w500/${movieInfo.movie.posterPath}")
                binding.text3.text = movieInfo.genres.joinToString { genre -> genre.genreName }
                binding.text4.text =
                    movieInfo.casts.sortedBy { it.order }.joinToString(limit = 5) { cast ->
                        cast.name
                    }
                binding.text5.text = movieInfo.crews.joinToString(limit = 5) { crew -> crew.name }
            }
        }

        observe(viewModel.loadMovieEvent) {
            it.onFailure { throwable ->
                Toast.makeText(
                        requireContext(),
                        "Error loading: ${throwable.localizedMessage}",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }
    }
}
