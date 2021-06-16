package wat.mobilne.renthome.models

import java.time.LocalDate
import java.util.*

data class Reservation(
    private val offerDto: Offer,
    private val userDto: User,
    private val startDate: Date?,
    private val endDate: Date?,
    private val dateOfCreate: Date?
)
