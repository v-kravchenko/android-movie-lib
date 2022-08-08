package com.redcatgames.movies.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redcatgames.movies.presentation.databinding.PopularFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PopularFragment : BaseFragment() {

    private val viewModel: PopularViewModel by viewModels()
    private var binding: PopularFragmentBinding by autoCleared {
        it.movieList.adapter = null
    }
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
        adapter.onItemClick =
            {
                navigateTo(
                    PopularFragmentDirections.actionPopularFragmentToMovieFragment(it.id, it.title)
                )
            }
        setupObserver()

        binding.movieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                (binding.movieList.layoutManager as? GridLayoutManager)?.let { gridLayoutManager ->
                    val visibleItemCount: Int = gridLayoutManager.childCount
                    val totalItemCount: Int = gridLayoutManager.itemCount
                    val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0
                    ) {
                        Timber.d("Load next page request!")
                    }
                }
            }
        })
    }

    private fun setupObserver() {

        viewModel.popularMovies.observe { adapter.setItems(it) }

        viewModel.events.observe { event ->
            when (event) {
                is PopularViewModel.Event.MoviesLoaded -> {
                    event.result.onSuccess {
                        showToast("Loaded ${it.size} movies")
                    }.onFailure { error ->
                        showToast("Error loading: ${error.message}")
                    }
                }
            }
        }
    }


}
