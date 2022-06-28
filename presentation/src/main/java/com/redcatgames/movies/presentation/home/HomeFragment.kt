package com.redcatgames.movies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.HomeFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: HomeFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    navigateTo(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
                    true
                }
                else -> false
            }
        }

        binding.buttonPopular.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToPopularFragment())
        }

        setupObserver()
    }

    private fun setupObserver() {}
}
