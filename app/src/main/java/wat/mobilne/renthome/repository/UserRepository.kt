package wat.mobilne.renthome.repository

import retrofit2.Response
import wat.mobilne.renthome.api.RetrofitInstance
import wat.mobilne.renthome.models.User
import wat.mobilne.renthome.utils.Preferences

class UserRepository {
    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

    suspend fun getUser(): Response<User> {
        return RetrofitInstance.api.getUser(Preferences.fcmToken)
    }

    suspend fun updateUser(username: String, name:String, surname:String, email: String): Response<User> {
        return  RetrofitInstance.api.updateUser(username, name, surname, email)
    }
}