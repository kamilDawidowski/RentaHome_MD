package wat.mobilne.renthome.repository

import retrofit2.Response
import wat.mobilne.renthome.api.RetrofitInstance
import wat.mobilne.renthome.models.Reservation

class ReservationRepository {
    suspend fun getReservations(): Response<List<Reservation>> {
        return RetrofitInstance.api.getReservations()
    }

    suspend fun makeReservation(): Response<Reservation> {
        return RetrofitInstance.api.makeReservation()
    }
//
//    suspend fun acceptReservation(): Response<List<Reservation>> {
//        return RetrofitInstance.api.acceptReservation()
//    }
}