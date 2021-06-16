package wat.mobilne.renthome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import wat.mobilne.renthome.models.Registration
import wat.mobilne.renthome.models.RegistrationResponse
import wat.mobilne.renthome.repository.RegisterRepository

class RegisterViewModel: ViewModel() {
    val registerRepository: RegisterRepository = RegisterRepository()

    val registerResponse: MutableLiveData<Response<RegistrationResponse>> = MutableLiveData()
    fun register(registration: Registration) {
        viewModelScope.launch {
            val response = registerRepository.register(registration)
            registerResponse.value = response
        }
    }
}