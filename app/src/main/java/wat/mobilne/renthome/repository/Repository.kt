package com.wat.rentahome.repository

import com.wat.rentahome.api.UnauthorizedRetrofitInstance
import com.wat.rentahome.api.RetrofitInstance
import com.wat.rentahome.models.*
import retrofit2.Response
import wat.mobilne.renthome.models.Reservation

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

    suspend fun getReservations(): Response<List<Reservation>>? {
        return RetrofitInstance.api.getReservations()
    }

    suspend fun updateUser(username: String, email: String): Response<User> {
        return  RetrofitInstance.api.updateUser(username, email)
    }
}