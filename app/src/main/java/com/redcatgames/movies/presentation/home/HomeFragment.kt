package com.redcatgames.movies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.redcatgames.movies.R
import com.redcatgames.movies.databinding.HomeFragmentBinding
import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.presentation.base.BaseFragment
import com.redcatgames.movies.presentation.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: HomeFragmentBinding by autoCleared()
    private val languageAdapter by lazy {
        ArrayAdapter<Language>(requireContext(), R.layout.list_item)
    }

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
        binding.buttonPopular.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToPopularFragment())
        }
        binding.buttonDeleteMovies.setOnClickListener {
            viewModel.deleteAllMovies()
        }
        (binding.spinnerLanguage.editText as? AutoCompleteTextView)?.setAdapter(languageAdapter)

        setupObserver()
    }

    private fun setupObserver() {

        observe(viewModel.languages) {
            languageAdapter.clear()
            languageAdapter.addAll(it)
        }

        observe(viewModel.language) {
            binding.textLanguage.setText(it?.toString(), false)
        }

        observe(viewModel.imageConfig) {
            Timber.w("ImageConfig: $it")
            binding.textImageBaseUrl.setText(it.baseUrl)
            binding.textImageSecureBaseUrl.setText(it.secureBaseUrl)
        }

        observe(viewModel.loadDictionaryEvent) {
            it.onSuccess {
                Timber.d("Dictionary loaded")
            }.onFailure { errorMessage ->
                Timber.d("Error loading config: $errorMessage")
            }
        }

        observe(viewModel.deleteAllMoviesEvent) {
            it.onSuccess { movieCount ->
                Toast.makeText(requireContext(), "Removed $movieCount movies", Toast.LENGTH_SHORT)
                    .show()
            }.onFailure { errorMessage ->
                Toast.makeText(requireContext(), "Error $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}