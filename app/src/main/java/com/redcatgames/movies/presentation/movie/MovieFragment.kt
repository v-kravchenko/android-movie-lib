package com.redcatgames.movies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.imageLoader
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.redcatgames.movies.databinding.HomeFragmentBinding
import com.redcatgames.movies.databinding.MovieFragmentBinding
import com.redcatgames.movies.presentation.base.BaseFragment
import com.redcatgames.movies.presentation.util.autoCleared
import com.redcatgames.movies.presentation.util.loadByUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val args by navArgs<MovieFragmentArgs>()
    private val viewModel: MovieViewModel by viewModels()
    private var binding: MovieFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {

        observe(viewModel.movieInfo) { movieInfo ->
            movieInfo?.let {
                binding.text1.text = it.movie.title
                binding.text2.text = it.movie.overview
                binding.posterImage.loadByUrl("w500/${it.movie.posterPath}")
                binding.text3.text = it.genres.joinToString { genre -> genre.genreName }
                binding.text4.text = it.casts.joinToString(limit = 5) { cast -> cast.name }
                binding.text5.text = it.crews.joinToString(limit = 5) { crew -> crew.name }
            }
        }

        observe(viewModel.loadMovieEvent) {
            it.onFailure { errorMessage ->
                Toast.makeText(requireContext(), "Error loading: $errorMessage", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}