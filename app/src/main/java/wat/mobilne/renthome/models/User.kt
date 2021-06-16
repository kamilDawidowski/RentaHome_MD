package wat.mobilne.renthome.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User (
    val username: String,
    val name: String,
    val surname: String,
    val email: String,
    val description: String
): Parcelable