package com.redcatgames.movies.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.fragment.app.viewModels
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.SettingsFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private val viewModel: SettingsViewModel by viewModels()
    private var binding: SettingsFragmentBinding by autoCleared()
    private val languageAdapter by lazy { LanguageAdapter(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    viewModel.save()
                    true
                }
                else -> false
            }
        }

        (binding.spinnerLanguage.editText as? AutoCompleteTextView)?.let {
            it.setAdapter(languageAdapter)
            it.setOnItemClickListener { _, _, position, _ ->
                languageAdapter.getItem(position)?.let { language ->
                    viewModel.setApiLanguage(language.iso)
                }
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val value =
                if (binding.radioDarkYes.isChecked) MODE_NIGHT_YES
                else if (binding.radioDarkNo.isChecked) MODE_NIGHT_NO else MODE_NIGHT_FOLLOW_SYSTEM
            viewModel.setUiDarkMode(value)
        }

        setupObserver()
    }

    private fun setState(state: SettingsViewModel.State) {
        Timber.d("ViewModelState is ${state.name()}")

        when (state) {
            SettingsViewModel.State.Empty -> {}
            is SettingsViewModel.State.Saved -> {
                setDefaultNightMode(state.darkMode)
                navigateTo(SettingsFragmentDirections.actionSettingsFragmentToSplashFragment())
            }
            is SettingsViewModel.State.Data -> {
                binding.textLanguage.setText(state.language?.englishName, false)
                binding.radioDarkSystem.isChecked = state.darkMode == MODE_NIGHT_FOLLOW_SYSTEM
                binding.radioDarkYes.isChecked = state.darkMode == MODE_NIGHT_YES
                binding.radioDarkNo.isChecked = state.darkMode == MODE_NIGHT_NO
            }
        }
    }

    private fun setupObserver() {

        viewModel.languages.observe {
            languageAdapter.clear()
            languageAdapter.addAll(it)
        }

        viewModel.state.observe { state -> setState(state) }
    }
}
