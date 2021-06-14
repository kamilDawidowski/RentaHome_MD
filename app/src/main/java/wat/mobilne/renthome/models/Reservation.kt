package wat.mobilne.renthome.models

import com.wat.rentahome.models.Offer
import com.wat.rentahome.models.User
import java.time.LocalDate

data class Reservation(
    private val offerDto: Offer,
    private val userDto: User,
    private val startDate: LocalDate,
    private val endDate: LocalDate,
    private val dateOfCreate: LocalDate
)
