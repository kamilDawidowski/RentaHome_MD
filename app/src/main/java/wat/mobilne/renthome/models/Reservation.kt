package wat.mobilne.renthome.models

import java.time.LocalDate
import java.util.*

data class Reservation(
    val id: Long?,
    val offerDto: Offer,
    val userDto: User,
    val startDate: String,
    val endDate: String,
)
