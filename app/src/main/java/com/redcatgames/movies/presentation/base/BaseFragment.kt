package com.redcatgames.movies.presentation.base

import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragment : Fragment() {

    protected fun <T> observe(liveData: LiveData<T>, observer: Observer<in T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }

    protected fun navigateBack() {
        findNavController().popBackStack()
    }

    protected fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

    protected fun isNightMode() = AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES

    protected fun toggleNightMode() {
        AppCompatDelegate.setDefaultNightMode(if (isNightMode()) MODE_NIGHT_NO else MODE_NIGHT_YES)
    }
}
