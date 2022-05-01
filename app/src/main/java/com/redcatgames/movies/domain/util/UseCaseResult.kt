package com.redcatgames.movies.domain.util

sealed class UseCaseResult<out T> {

    inline fun onSuccess(callback: (value: T) -> Unit) {
        if (this is Success) {
            callback(value)
            Result
        }
    }

    inline fun onFailure(callback: (errorMessage: String?) -> Unit) {
        if (this is Failure) {
            callback(errorMessage)
        }
    }

    data class Success<T>(val value: T) : UseCaseResult<T>()
    data class Failure<T>(val errorMessage: String?) : UseCaseResult<T>()

}