package com.redcatgames.movies.data.source.remote.adapter

import java.io.IOException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

/**
 * [Call] interface implementation in order to make Retrofit return [NetworkResponse] when API call
 * is triggered.
 */
internal class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>> {

    private companion object {
        const val ERROR_EMPTY_BODY = "Empty body"
    }

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(
            object : Callback<S> {
                override fun onResponse(call: Call<S>, response: Response<S>) {
                    val body = response.body()
                    val code = response.code()
                    val error = response.errorBody()

                    if (response.isSuccessful) {
                        enqueueSuccess(callback, body)
                    } else {
                        enqueueNotSuccess(callback, error, code)
                    }
                }

                override fun onFailure(call: Call<S>, throwable: Throwable) {
                    enqueueFailure(callback, throwable)
                }
            }
        )
    }

    private fun enqueueFailure(callback: Callback<NetworkResponse<S, E>>, throwable: Throwable) {
        val networkResponse =
            when (throwable) {
                is IOException -> NetworkResponse.NetworkError(throwable)
                else -> NetworkResponse.UnknownError(throwable)
            }
        callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
    }

    private fun enqueueNotSuccess(
        callback: Callback<NetworkResponse<S, E>>,
        error: ResponseBody?,
        code: Int
    ) {
        val errorBody =
            when {
                error == null -> null
                error.contentLength() == 0L -> null
                else ->
                    try {
                        errorConverter.convert(error)
                    } catch (e: Exception) {
                        null
                    }
            }
        if (errorBody != null) {
            // Response is not successful
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(NetworkResponse.ApiError(errorBody, code))
            )
        } else {
            // Response is not successful but the error body is null
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(NetworkResponse.UnknownError(Exception(ERROR_EMPTY_BODY)))
            )
        }
    }

    private fun enqueueSuccess(callback: Callback<NetworkResponse<S, E>>, body: S?) {
        if (body != null) {
            // Response is successful
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(NetworkResponse.Success(body))
            )
        } else {
            // Response is successful but the body is null
            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(NetworkResponse.UnknownError(Exception(ERROR_EMPTY_BODY)))
            )
        }
    }

    override fun isExecuted() = delegate.isExecuted

    override fun timeout(): Timeout = delegate.timeout()

    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
}
