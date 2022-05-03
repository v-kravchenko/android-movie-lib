package com.redcatgames.movies.domain.util

sealed class UseCaseResult<out T, out U> {

    inline fun onSuccess(action: (value: T) -> Unit) : UseCaseResult<T, U> {
        if (this is Success) {
            action(value)
        }
        return this
    }

    inline fun onFailure(action: (error: U) -> Unit): UseCaseResult<T, U> {
        if (this is Failure) {
            action(error)
        }
        return this
    }

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this !is Success

    data class Success<T, U>(val value: T) : UseCaseResult<T, U>()
    data class Failure<T, U>(val error: U) : UseCaseResult<T, U>()

}