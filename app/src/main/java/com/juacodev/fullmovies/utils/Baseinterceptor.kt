package com.juacodev.fullmovies.utils

import com.juacodev.fullmovies.data.network.ServerUrls.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class Baseinterceptor :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", API_KEY)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}