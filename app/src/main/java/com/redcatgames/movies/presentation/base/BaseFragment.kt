package com.redcatgames.movies.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.redcatgames.movies.presentation.util.SingleLiveEvent

open class BaseFragment: Fragment() {

    protected fun <T> observe(liveData: LiveData<T>, observer: Observer<in T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }

    /*protected fun <T> observe(liveData: SingleLiveEvent<T>, observer: Observer<in T>) {
        liveData.observe(viewLifecycleOwner, observer)
    }*/

}