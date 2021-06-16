package wat.mobilne.renthome.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Offer(
    val id: Long?,
    val userDto: User,
    val title: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val price: Double,
): Parcelable
