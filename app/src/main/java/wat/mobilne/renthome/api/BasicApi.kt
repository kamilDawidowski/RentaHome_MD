package com.wat.rentahome.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import wat.mobilne.renthome.models.*

interface BasicApi {

    @GET("user")
    suspend fun getUsers() : Response<List<User>>

    @GET("user/login")
    suspend fun getUser(@Header("Content-Type") contentType: String="application/json") : Response<User>

    @GET("offer")
    suspend fun getOffers() : Response<List<Offer>>

    @POST("registration")
    suspend fun register(@Body registration: Registration): Response<RegistrationResponse>

    @GET("reservation")
    suspend fun getReservations(): Response<List<Reservation>>

    @POST("offer")
    suspend fun createOffer(@Body offer: Offer): Response<Offer>

    @PUT("user")
    suspend fun updateUser(
        @Query("username") username: String,
        @Query("description") description: String) : Response<User>


    @Multipart
    @POST("image")
    suspend fun uploadImage(@Part("image") multipartImage: MultipartBody, contentType: String): Response<ResponseBody>
}