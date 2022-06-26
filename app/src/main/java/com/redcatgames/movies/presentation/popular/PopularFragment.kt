package com.redcatgames.movies.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.redcatgames.movies.databinding.PopularFragmentBinding
import com.redcatgames.movies.presentation.base.BaseFragment
import com.redcatgames.movies.presentation.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseFragment() {

    private val viewModel: PopularViewModel by viewModels()
    private var binding: PopularFragmentBinding by autoCleared()
    private val adapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PopularFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieList.adapter = adapter
        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        setupObserver()
    }

    private fun setupObserver() {

        observe(viewModel.popularMovies) { adapter.setItems(it) }

        adapter.onItemClick = {
            navigateTo(PopularFragmentDirections.actionPopularFragmentToMovieFragment(it.id))
        }

        observe(viewModel.loadPopularMoviesEvent) {
            it.onSuccess { movies ->
                    Toast.makeText(
                            requireContext(),
                            "Loaded ${movies.size} movies!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                .onFailure { errorMessage ->
                    Toast.makeText(
                            requireContext(),
                            "Error loading: $errorMessage",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
        }
    }
}
