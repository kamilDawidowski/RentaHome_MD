package com.wat.rentahome.api

import com.wat.rentahome.models.*
import retrofit2.Response
import retrofit2.http.*
import wat.mobilne.renthome.models.Reservation

interface BasicApi {

    @GET("user")
    suspend fun getUsers() : Response<List<User>>

    @GET("user/login")
    suspend fun getUser() : Response<User>

    @GET("offer")
    suspend fun getOffers() : Response<List<Offer>>

    @POST("registration")
    suspend fun register(@Body registration: Registration): Response<RegistrationResponse>

    @GET("reservation")
    suspend fun getReservations(): Response<List<Reservation>>

}