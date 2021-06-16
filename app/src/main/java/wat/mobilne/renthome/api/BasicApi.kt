package com.wat.rentahome.api

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import wat.mobilne.renthome.models.*
import java.time.LocalDate

interface BasicApi {

    //  User
    @GET("user")
    suspend fun getUsers() : Response<List<User>>

    @PUT("user/login")
    suspend fun getUser(@Query("fcmToken") fcmToken: String?) : Response<User>

    @PUT("user")
    suspend fun updateUser(
        @Query("username") username: String,
        @Query("name") name: String,
        @Query("surname") surname: String,
        @Query("description") description: String) : Response<User>


    //  Offer
    @GET("offer")
    suspend fun getOffers() : Response<List<Offer>>

    @POST("offer")
    suspend fun createOffer(@Body offer: Offer): Response<Offer>


    //  Reservation
    @GET("reservation")
    suspend fun getReservations(): Response<List<Reservation>>

    @POST("reservation")
    suspend fun makeReservation(@Body reservation: Reservation): Response<Reservation>

    @PUT("reservation")
    suspend fun acceptReservation(@Body reservation: Reservation): Response<Reservation>

    @DELETE("reservation")
    suspend fun rejectReservation(@Body reservation: Reservation): Response<Boolean>


    //  Registration
    @POST("registration")
    suspend fun register(@Body registration: Registration): Response<RegistrationResponse>

    //  Image
    @Multipart
    @POST("image")
    suspend fun uploadImage(@Part image: MultipartBody.Part): Response<ResponseBody>

    @PUT("user/password")
    suspend fun changePassword(@Query("oldPassword") oldPassword: String,
                               @Query("newPassword") newPassword: String): Response<User>
}