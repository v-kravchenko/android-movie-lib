package com.redcatgames.movies.domain.util

sealed class UseCaseResult<out T : Any> {

    inline fun onSuccess(callback: (value: T) -> Unit) {
        if (this is Success) {
            callback(value)
        }
    }

    inline fun onFailure(callback: (errorMessage: String?) -> Unit) {
        if (this is Failure) {
            callback(errorMessage)
        }
    }

    data class Success<T : Any>(val value: T) : UseCaseResult<T>()
    data class Failure<T : Any>(val errorMessage: String?) : UseCaseResult<T>()

}