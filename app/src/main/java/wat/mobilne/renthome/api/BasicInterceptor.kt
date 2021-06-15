package wat.mobilne.renthome.api

import okhttp3.Interceptor
import okhttp3.Response
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