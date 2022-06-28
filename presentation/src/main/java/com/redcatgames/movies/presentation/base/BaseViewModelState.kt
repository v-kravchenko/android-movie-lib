package com.redcatgames.movies.presentation.base

open class BaseViewModelState {
    fun name(): String = this.javaClass.simpleName
}
