package com.redcatgames.movies.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragment: Fragment() {

    protected fun <T> observe(liveData: LiveData<T>, observer: Observer<in T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }

    protected fun goBack() {
        findNavController().popBackStack()
    }

    protected fun navigate(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

}