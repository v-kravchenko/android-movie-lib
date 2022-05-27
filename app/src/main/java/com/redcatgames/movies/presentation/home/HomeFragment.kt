package com.redcatgames.movies.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.redcatgames.movies.R
import com.redcatgames.movies.databinding.HomeFragmentBinding
import com.redcatgames.movies.presentation.base.BaseFragment
import com.redcatgames.movies.presentation.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    companion object {
        private const val KEY_SAVE_LANGUAGE = "language"
    }

    private val viewModel: HomeViewModel by viewModels()
    private var binding: HomeFragmentBinding by autoCleared()
    private val languageAdapter by lazy { LanguageAdapter(requireContext()) }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.w("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.w("onDestroy()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.w("onSaveInstanceState()")
        try {
            outState.putString(KEY_SAVE_LANGUAGE, binding.textLanguage.text.toString())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        savedInstanceState?.getString(KEY_SAVE_LANGUAGE)?.let {
            binding.textLanguage.setText(it, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    Snackbar.make(binding.root, item.title, Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.action_night_mode -> {
                    toggleNightMode()
                    true
                }
                else -> false
            }
        }

        binding.buttonPopular.setOnClickListener {
            navigateTo(HomeFragmentDirections.actionHomeFragmentToPopularFragment())
        }

        (binding.spinnerLanguage.editText as? AutoCompleteTextView)?.let {
            it.setAdapter(languageAdapter)
            it.setOnItemClickListener { _, _, position, _ ->
                languageAdapter.getItem(position)?.let { language ->
                    viewModel.setApiLanguage(language)
                }
            }
        }

        setupObserver()
    }

    private fun setupObserver() {

        observe(viewModel.languages) {
            languageAdapter.clear()
            languageAdapter.addAll(it)
        }

        observe(viewModel.language) { binding.textLanguage.setText(it?.englishName, false) }

        observe(viewModel.loadDictionaryEvent) {
            it.onFailure { throwable ->
                Timber.d("Error loading config: ${throwable.localizedMessage}")
            }
        }
    }
}
