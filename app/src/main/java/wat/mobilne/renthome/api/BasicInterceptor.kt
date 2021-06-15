package com.wat.rentahome.api

import android.content.Context
import android.util.Log
import com.wat.rentahome.utils.Constants
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.Header
import wat.mobilne.renthome.utils.Preferences

class BasicInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .addHeader("Authorization", Preferences.basicToken!!)
            .build()
        return chain.proceed(request)
    }
}