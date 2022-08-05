package com.redcatgmes.movies.baseui

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseFragment : Fragment() {

    fun <T> LiveData<T>.observe(observer: Observer<in T>) {
        this.observe(viewLifecycleOwner, observer)
    }

    fun <T> Flow<T>.observe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        observer: (T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            flowWithLifecycle(lifecycle, state)
                .collect { value ->
                    observer(value)
                }
        }
    }

    protected fun navigateBack() {
        findNavController().navigateUp()
    }

    protected fun navigateTo(navDirections: NavDirections) {
        findNavController().navigate(navDirections)
    }

}
