package wat.mobilne.renthome.repository

import wat.mobilne.renthome.api.UnauthorizedRetrofitInstance
import wat.mobilne.renthome.api.RetrofitInstance
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import wat.mobilne.renthome.models.*

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

    suspend fun getReservations(): Response<List<Reservation>> {
        return RetrofitInstance.api.getReservations()
    }

    suspend fun updateUser(username: String, email: String): Response<User> {
        return  RetrofitInstance.api.updateUser(username, email)
    }

    suspend fun createOffer(offer: Offer): Response<Offer> {
        return RetrofitInstance.api.createOffer(offer)
    }

    suspend fun uploadImage(image: MultipartBody, contentType: String): Response<ResponseBody> {
        return RetrofitInstance.api.uploadImage(image, contentType)
    }
}