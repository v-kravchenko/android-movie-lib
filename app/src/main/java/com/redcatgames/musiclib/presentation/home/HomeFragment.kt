package com.redcatgames.musiclib.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.redcatgames.musiclib.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

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
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.text1.text = this.javaClass.simpleName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}