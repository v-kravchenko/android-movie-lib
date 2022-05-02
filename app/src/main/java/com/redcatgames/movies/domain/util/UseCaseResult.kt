package com.redcatgames.movies.domain.util

sealed class UseCaseResult<out T> {

    inline fun onSuccess(action: (value: T) -> Unit) : UseCaseResult<T> {
        if (this is Success) {
            action(value)
        }
        return this
    }

    inline fun onFailure(action: (errorMessage: String?) -> Unit): UseCaseResult<T> {
        if (this is Failure) {
            action(errorMessage)
        }
        return this
    }

    data class Success<T>(val value: T) : UseCaseResult<T>()
    data class Failure<T>(val errorMessage: String?) : UseCaseResult<T>()

}