package wat.mobilne.renthome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import wat.mobilne.renthome.models.Reservation
import wat.mobilne.renthome.models.User
import wat.mobilne.renthome.repository.ReservationRepository
import wat.mobilne.renthome.repository.UserRepository

class ReservationViewModel: ViewModel() {
    val reservationRepository: ReservationRepository = ReservationRepository()

    val reservationsResponse: MutableLiveData<Response<List<Reservation>>> = MutableLiveData()
    val makeReservationResponse: MutableLiveData<Response<Reservation>> = MutableLiveData()
    val acceptReservationResponse: MutableLiveData<Response<Reservation>> = MutableLiveData()

    fun getReservations() {
        viewModelScope.launch {
            val response = reservationRepository.getReservations()
            reservationsResponse.value = response
        }
    }

    fun makeReservation(reservation: Reservation) {
        viewModelScope.launch {
            val response = reservationRepository.makeReservation(reservation)
            makeReservationResponse.value = response
        }
    }
}