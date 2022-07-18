package com.redcatgames.movies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redcatgames.movies.presentation.databinding.MovieFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import com.redcatgmes.movies.baseui.util.loadByUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val args by navArgs<MovieFragmentArgs>()
    private val viewModel: MovieViewModel by viewModels()
    private var binding: MovieFragmentBinding by autoCleared()
    private val castAdapter: CastAdapter by lazy {
        CastAdapter()
    }

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
        binding.topAppBar.title = args.movieTitle
        binding.castList.adapter = castAdapter

        setupObserver()
    }

    private fun setupObserver() {

        viewModel.movieInfo.observe { info ->
            info?.let { movieInfo ->
                binding.textTitle.text = movieInfo.movie.title

                binding.textRating.text = movieInfo.movie.voteRating
                binding.textOverview.text = movieInfo.movie.overview
                binding.posterImage.loadByUrl("w342${movieInfo.movie.posterPath}")
                binding.textGenres.text = movieInfo.genres.joinToString { genre -> genre.genreName }

                castAdapter.setItems(movieInfo.casts.sortedBy { it.order })

//                binding.text4.text =
//                    movieInfo.casts.sortedBy { it.order }.joinToString { cast -> cast.name }
//                binding.text5.text = movieInfo.crews.joinToString { crew -> crew.name }

                binding.backdropImage.loadByUrl("w780${movieInfo.movie.backdropPath}")
            }
        }

        viewModel.loadMovieEvent.observe {
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
