package com.redcatgames.movies.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import javax.inject.Inject

open class BaseFragment: Fragment() {

    @Inject
    protected lateinit var imageLoader: ImageLoader

    protected fun <T> observe(liveData: LiveData<T>, observer: Observer<in T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }

    protected fun navigateBack() {
        findNavController().popBackStack()
    }

    protected fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

}