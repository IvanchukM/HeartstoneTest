package com.example.heartstonetestapp.network.util

import com.example.heartstonetestapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    return chain.proceed(
      chain.request().newBuilder()
        .addHeader(HTTPHeaders.HOST_HEADER, BuildConfig.API_HOST)
        .addHeader(HTTPHeaders.API_KEY_HEADER, BuildConfig.API_KEY)
        .build()
    )
  }
}
