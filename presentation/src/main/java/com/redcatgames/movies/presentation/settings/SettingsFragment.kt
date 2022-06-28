package com.redcatgames.movies.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.redcatgames.movies.presentation.R
import com.redcatgames.movies.presentation.databinding.SettingsFragmentBinding
import com.redcatgmes.movies.baseui.BaseFragment
import com.redcatgmes.movies.baseui.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment() {

    private val viewModel: SettingsViewModel by viewModels()
    private var binding: SettingsFragmentBinding by autoCleared()

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
                    Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
                    navigateBack()
                    true
                }
                else -> false
            }
        }
        setupObserver()
    }

    private fun setupObserver() {}
}
