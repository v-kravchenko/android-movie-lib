package com.redcatgames.movies.presentation.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.redcatgames.movies.presentation.databinding.SplashFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()
    private var binding: SplashFragmentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener { viewModel.loadDictionary() }
        setupObserver()
    }

    private fun setState(state: SplashViewModel.State) {
        Timber.d("ViewModelState is ${state.name()}")

        when (state) {
            SplashViewModel.State.Loading -> {
                binding.viewStateLoading.visibility = View.VISIBLE
                binding.viewStateFailed.visibility = View.GONE
            }
            SplashViewModel.State.Failed -> {
                binding.viewStateFailed.visibility = View.VISIBLE
                binding.viewStateLoading.visibility = View.GONE
            }
            SplashViewModel.State.Success -> {
                navigateTo(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }

    private fun setupObserver() {
        observe(viewModel.dictionaryInfo) { Timber.d("Dictionary info: $it") }

        viewModel.state.observe { state -> setState(state) }
    }
}
