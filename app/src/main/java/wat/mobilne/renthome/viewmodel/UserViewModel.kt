package wat.mobilne.renthome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import wat.mobilne.renthome.models.User
import wat.mobilne.renthome.repository.UserRepository

class UserViewModel: ViewModel() {
    val userRepository: UserRepository = UserRepository()

    val usersResponse: MutableLiveData<Response<List<User>>> = MutableLiveData()
    val userResponse: MutableLiveData<Response<User>> = MutableLiveData()
    val updateUserResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun getUsers() {
        viewModelScope.launch {
            val response = userRepository.getUsers()
            usersResponse.value = response
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val response = userRepository.getUser()
            userResponse.value = response
        }
    }

    fun updateUser(username: String, name: String, surname: String, description: String) {
        viewModelScope.launch {
            val response = userRepository.updateUser(username, name, surname, description)
            updateUserResponse.value = response
        }
    }

    val uploadImageResponse: MutableLiveData<Response<ResponseBody>> = MutableLiveData()
    fun uploadImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            val response = userRepository.uploadImage(image)
            uploadImageResponse.value = response
        }
    }

    val changePasswordResponse: MutableLiveData<Response<User>> = MutableLiveData()
    fun changePassword(oldPassword: String, newPassword: String) {
        viewModelScope.launch {
            val response = userRepository.changePassword(oldPassword, newPassword)
            changePasswordResponse.value = response
        }
    }
}