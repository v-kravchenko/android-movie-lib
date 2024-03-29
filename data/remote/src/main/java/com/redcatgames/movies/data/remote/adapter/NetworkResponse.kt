package com.redcatgames.movies.data.remote.adapter

sealed class NetworkResponse<out T : Any, out U : Any> {

    inline fun onSuccess(callback: (value: T) -> Unit) {
        if (this is Success) {
            callback(this.body)
        }
    }

    inline fun onApiError(callback: (error: U, code: Int) -> Unit) {
        if (this is ApiError) {
            callback(this.body, code)
        }
    }

    inline fun onNetworkError(callback: (error: Throwable) -> Unit) {
        if (this is NetworkError) {
            callback(this.error)
        }
    }

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this !is Success

    /** Represents success response with body. */
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()

    /** Represents failure response (non-2xx) with body. */
    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()

    /** Represents network failure (such as no internet connection). */
    data class NetworkError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()
}
