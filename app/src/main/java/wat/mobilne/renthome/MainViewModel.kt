package wat.mobilne.renthome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import wat.mobilne.renthome.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import wat.mobilne.renthome.models.*

class MainViewModel(private val repository: Repository): ViewModel() {

    val usersResponse: MutableLiveData<Response<List<User>>> = MutableLiveData()
    fun getUsers() {
        viewModelScope.launch {
            val response = repository.getUsers()
            usersResponse.value = response
        }
    }

    val userResponse: MutableLiveData<Response<User>> = MutableLiveData()
    fun getUser() {
        viewModelScope.launch {
            val response = repository.getUser()
            userResponse.value = response
        }
    }

    val offersResponse: MutableLiveData<Response<List<Offer>>> = MutableLiveData()
    fun getOffers() {
        viewModelScope.launch {
            val response = repository.getOffers()
            offersResponse.value = response
        }
    }

    val registerResponse: MutableLiveData<Response<RegistrationResponse>> = MutableLiveData()
    fun register(registration: Registration) {
        viewModelScope.launch {
            val response = repository.register(registration)
            registerResponse.value = response
        }
    }

    val reservationsResponse: MutableLiveData<Response<List<Reservation>>> = MutableLiveData()
    fun getReservations() {
        viewModelScope.launch {
            val response = repository.getReservations()
            reservationsResponse.value = response
        }
    }

    val updateUserResponse: MutableLiveData<Response<User>> = MutableLiveData()
    fun updateUser(username: String, name: String, surname: String, description: String) {
        viewModelScope.launch {
            val response = repository.updateUser(username, name, surname, description)
            updateUserResponse.value = response
        }
    }

    val createOfferResponse: MutableLiveData<Response<Offer>> = MutableLiveData()
    fun createOffer(offer: Offer) {
        viewModelScope.launch {
            val response = repository.createOffer(offer)
            createOfferResponse.value = response
            getOffers()
        }
    }

    val uploadImageResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    fun uploadImage(image: MultipartBody, contentType: String) {
        viewModelScope.launch {
            val response = repository.uploadImage(image, contentType)
            uploadImageResponse.value = response
        }
    }

}