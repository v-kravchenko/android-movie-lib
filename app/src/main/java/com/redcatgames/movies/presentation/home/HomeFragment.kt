package com.redcatgames.movies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.redcatgames.movies.databinding.HomeFragmentBinding
import com.redcatgames.movies.presentation.base.BaseFragment
import com.redcatgames.movies.presentation.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: HomeFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text1.text = this.javaClass.simpleName
        setupObserver()
    }

    private fun setupObserver() {

        observe(viewModel.popularMovieList) {
            Timber.d("Popular movie count: ${it.size}")
        }

        observe(viewModel.loadPopularMovieListEvent) {
            it.onSuccess { movieCount ->
                Toast.makeText(requireContext(), "Loaded $movieCount movies!", Toast.LENGTH_SHORT).show()
            }
            it.onFailure { errorMessage ->
                Toast.makeText(requireContext(), "Error loading: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}