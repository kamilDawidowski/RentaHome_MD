package com.wat.rentahome.repository

import com.wat.rentahome.api.UnauthorizedRetrofitInstance
import com.wat.rentahome.api.RetrofitInstance
import com.wat.rentahome.models.*
import retrofit2.Response

class Repository {

    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getUser(): Response<User> {
        return RetrofitInstance.api.getUser()
    }

    suspend fun getOffers(): Response<List<Offer>> {
        return RetrofitInstance.api.getOffers()
    }

    suspend fun register(registration: Registration): Response<RegistrationResponse> {
        return UnauthorizedRetrofitInstance.api.register(registration)
    }
}