package com.redcatgames.movies.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.redcatgames.movies.domain.model.MovieCrew
import com.redcatgames.movies.presentation.*
import com.redcatgames.movies.presentation.databinding.MovieFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import com.redcatgmes.movies.baseui.util.currentLocale
import com.redcatgmes.movies.baseui.util.loadByUrl
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val args by navArgs<MovieFragmentArgs>()
    private val viewModel: MovieViewModel by viewModels()
    private var binding: MovieFragmentBinding by autoCleared {
        it.castList.adapter = null
    }
    private val castAdapter: CastAdapter = CastAdapter()

    private val dateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("dd.MM.yyyy", requireContext().currentLocale)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        binding.topAppBar.title = args.movieTitle
        binding.castList.adapter = castAdapter

        castAdapter.onItemClick =
            {
                navigateTo(
                    MovieFragmentDirections.actionMovieFragmentToPersonFragment(
                        it.personId, it.name, it.gender))
            }

        setupObserver()
    }

    private fun setupObserver() {

        viewModel.movieInfo.observe { info ->
            info?.let { movieInfo ->
                binding.textTitle.text = movieInfo.movie.title

                binding.textRating.text = movieInfo.movie.voteRating
                binding.textOverview.text = movieInfo.movie.overview
                binding.posterImage.loadByUrl(movieInfo.movie.getPosterUri(PosterSize.SMALL)) {
                    placeholder(R.drawable.poster_placeholder_medium)
                    error(R.drawable.poster_placeholder_medium)
                }

                binding.textGenres.text =
                    buildSpannedString {
                        bold { append(getString(R.string.movie_genre_list_title)) }
                        append(" ${movieInfo.genres.joinToString { genre -> genre.genreName }}")
                    }
                binding.textReleaseDate.text =
                    buildSpannedString {
                        bold { append(getString(R.string.movie_release_date_title)) }
                        val releaseDate = movieInfo.movie.releaseDate
                        if (releaseDate == null) {
                            append(" -")
                        } else {
                            append(" ${dateFormat.format(releaseDate)}")
                        }
                    }

                castAdapter.setItems(movieInfo.casts.sortedBy { it.order })

                updateCrew(movieInfo.crews)

                binding.backdropImage.loadByUrl(movieInfo.movie.getBackdropUri(BackdropSize.MEDIUM))
            }
        }

        viewModel.events.observe { event ->
            when (event) {
                is MovieViewModel.Event.MovieInfoLoaded ->
                    event.result.onFailure { showToast("Error loading: ${it.message}") }
            }
        }
    }

    private fun updateCrew(crews: List<MovieCrew>) {
        val directorList = crews.filter { it.job == "Director" }
        val writerList = crews.filter { it.job == "Writer" }

        binding.textDirector.text =
            buildSpannedString {
                bold {
                    append(getString(R.string.movie_director_title))
                }
                append("\n")
                if (directorList.isEmpty()) {
                    append("-")
                } else {
                    append(directorList.joinToString { it.name })
                }
            }

        binding.textWriter.text =
            buildSpannedString {
                bold {
                    append(getString(R.string.movie_writer_title))
                }
                append("\n")
                if (writerList.isEmpty()) {
                    append("-")
                } else {
                    append(writerList.joinToString { it.name })
                }
            }
    }
}
