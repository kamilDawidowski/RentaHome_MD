package wat.mobilne.renthome.models

import java.time.LocalDate
import java.util.*

data class Reservation(
    val offerDto: Offer,
    val userDto: User,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val dateOfCreate: LocalDate?
)
