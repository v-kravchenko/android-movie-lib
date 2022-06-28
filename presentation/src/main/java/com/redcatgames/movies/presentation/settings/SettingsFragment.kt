package com.redcatgames.movies.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.fragment.app.viewModels
import com.redcatgames.movies.domain.model.Language
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.SettingsFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    companion object {
        private const val KEY_SAVE_LANGUAGE = "language"
    }

    private val viewModel: SettingsViewModel by viewModels()
    private var binding: SettingsFragmentBinding by autoCleared()

    private val languageAdapter by lazy { LanguageAdapter(requireContext()) }

    private var currentLanguage: Language? = null
    private var currentDarkMode: Int = MODE_NIGHT_FOLLOW_SYSTEM

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
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        savedInstanceState?.getString(KEY_SAVE_LANGUAGE)?.let {
            binding.textLanguage.setText(it, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_save -> {
                    viewModel.save(currentLanguage, currentDarkMode)
                    true
                }
                else -> false
            }
        }

        (binding.spinnerLanguage.editText as? AutoCompleteTextView)?.let {
            it.setAdapter(languageAdapter)
            it.setOnItemClickListener { _, _, position, _ ->
                languageAdapter.getItem(position)?.let { language -> setCurrentLanguage(language) }
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            val value =
                if (binding.radioDarkYes.isChecked) MODE_NIGHT_YES
                else if (binding.radioDarkNo.isChecked) MODE_NIGHT_NO else MODE_NIGHT_FOLLOW_SYSTEM
            currentDarkMode = value
        }

        setupObserver()
    }

    private fun setCurrentLanguage(language: Language?) {
        currentLanguage = language
        binding.textLanguage.setText(language?.englishName, false)
    }

    private fun setupObserver() {
        observe(viewModel.languages) {
            languageAdapter.clear()
            languageAdapter.addAll(it)
        }

        observe(viewModel.language) { setCurrentLanguage(it) }

        observe(viewModel.darkMode) {
            binding.radioDarkSystem.isChecked = it == MODE_NIGHT_FOLLOW_SYSTEM
            binding.radioDarkYes.isChecked = it == MODE_NIGHT_YES
            binding.radioDarkNo.isChecked = it == MODE_NIGHT_NO
            currentDarkMode = it
        }

        observe(viewModel.eventSaved) {
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            navigateTo(SettingsFragmentDirections.actionSettingsFragmentToSplashFragment())
        }
    }
}
