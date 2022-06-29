package com.redcatgames.movies.data.remote.source.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BearerLoginInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest =
            request.newBuilder().header("Authorization", "Bearer $token").build()
        return chain.proceed(authenticatedRequest)
    }
}
