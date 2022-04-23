package com.redcatgames.musiclib.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.redcatgames.musiclib.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding
        get() = _binding ?: throw RuntimeException("HomeFragmentBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text1.text = this.javaClass.simpleName
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.artistList.observe(viewLifecycleOwner) {

            Timber.d("Artist list: $it")

            Toast.makeText(
                requireContext(),
                "Loaded ${it.size} artist!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}