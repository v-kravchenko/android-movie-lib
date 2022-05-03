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

        val imageUrl = "https://image.tmdb.org/t/p/w500/g4tMniKxol1TBJrHlAtiDjjlx4Q.jpg"

        val imageLoader = binding.posterImage.context.imageLoader
        val request = ImageRequest.Builder(binding.posterImage.context)
            .data(imageUrl)
            .target(binding.posterImage)
            .build()

        binding.posterImage.load(imageUrl)

//        lifecycleScope.launch {
//            when (val res = imageLoader.execute(request)) {
//                is ErrorResult -> {
//                    Timber.e(res.throwable)
//                }
//                is SuccessResult -> {
//
//                }
//            }
//        }
    }

    private fun setupObserver() {

        observe(viewModel.movie) { movie ->
            movie?.let {
                binding.text1.text = it.title
                binding.text2.text = it.overview
                binding.posterImage.load("https://image.tmdb.org/t/p/w500/${it.posterPath}")
            }
        }

        observe(viewModel.loadMovieEvent) {
            it.onSuccess {
                Toast.makeText(requireContext(), "Loaded movie!", Toast.LENGTH_SHORT).show()
            }.onFailure { errorMessage ->
                Toast.makeText(requireContext(), "Error loading: $errorMessage", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}