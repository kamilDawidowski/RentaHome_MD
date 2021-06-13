package com.wat.rentahome.api

import com.wat.rentahome.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UnauthorizedRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BasicApi by lazy {
        retrofit.create(BasicApi::class.java)
    }
}