package wat.mobilne.renthome.repository

import retrofit2.Response
import wat.mobilne.renthome.api.UnauthorizedRetrofitInstance
import wat.mobilne.renthome.models.Registration
import wat.mobilne.renthome.models.RegistrationResponse

class RegisterRepository {
    suspend fun register(registration: Registration): Response<RegistrationResponse> {
        return UnauthorizedRetrofitInstance.api.register(registration)
    }

}