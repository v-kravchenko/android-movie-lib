package com.redcatgames.movies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redcatgames.movies.presentation.*
import com.redcatgames.movies.presentation.databinding.MovieFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import com.redcatgmes.movies.baseui.util.loadByUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val args by navArgs<MovieFragmentArgs>()
    private val viewModel: MovieViewModel by viewModels()
    private var binding: MovieFragmentBinding by autoCleared {
        it.castList.adapter = null
        it.crewList.adapter = null
    }
    private val castAdapter: CastAdapter = CastAdapter()
    private val crewAdapter: CrewAdapter = CrewAdapter()

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
        binding.crewList.adapter = crewAdapter

        castAdapter.onItemClick = {
            navigateTo(MovieFragmentDirections.actionMovieFragmentToPersonFragment(it.id, it.name, it.gender))
        }
        crewAdapter.onItemClick = {
            navigateTo(MovieFragmentDirections.actionMovieFragmentToPersonFragment(it.id, it.name, it.gender))
        }

        setupObserver()
    }

    private fun setupObserver() {

        viewModel.movieInfo.observe { info ->
            info?.let { movieInfo ->
                binding.textTitle.text = movieInfo.movie.title

                binding.textRating.text = movieInfo.movie.voteRating
                binding.textOverview.text = movieInfo.movie.overview
                binding.posterImage.loadByUrl(movieInfo.movie.getPosterUri(PosterSize.SMALL))

                binding.textGenres.text = buildSpannedString {
                    bold {
                        append(getString(R.string.movie_genre_list_title))
                    }
                    append(" ${movieInfo.genres.joinToString { genre -> genre.genreName }}")
                }
                binding.textReleaseDate.text = buildSpannedString {
                    bold {
                        append(getString(R.string.movie_release_date_title))
                    }
                    append(" ${movieInfo.movie.releaseDate}")
                }

                castAdapter.setItems(movieInfo.casts.sortedBy { it.order })
                crewAdapter.setItems(movieInfo.crews)

                binding.backdropImage.loadByUrl(movieInfo.movie.getBackdropUri(BackdropSize.MEDIUM))
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
