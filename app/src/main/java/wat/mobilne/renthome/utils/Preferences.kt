package wat.mobilne.renthome.utils

import android.content.Context
import android.content.SharedPreferences
import com.wat.rentahome.utils.Constants
import androidx.core.content.edit
import com.wat.rentahome.models.User

object Preferences {
    private var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
    }

    var userEmail: String?
        get() = Key.USER_EMAIL.getString()
        set(value) = Key.USER_EMAIL.setString(value)

    var basicToken: String?
        get() = Key.BASIC_TOKEN.getString()
        set(value) = Key.BASIC_TOKEN.setString(value)

    fun saveUser(user: User) {
        Key.USER_EMAIL.setString(user.email)
        Key.USER_USERNAME.setString(user.username)
        Key.USER_NAME.setString(user.name)
        Key.USER_SURNAME.setString(user.surname)
        Key.USER_BIRTHDAY.setString(user.dateOfBirth.toString())
    }

    var user: User
        get() = User(
            Key.USER_USERNAME.getString()!!,
            Key.USER_NAME.getString()!!,
            Key.USER_SURNAME.getString()!!,
            Key.USER_EMAIL.getString()!!,
            null,
            null)
        set(value: User) {
            Key.USER_EMAIL.setString(value.email)
            Key.USER_USERNAME.setString(value.username)
            Key.USER_NAME.setString(value.name)
            Key.USER_SURNAME.setString(value.surname)
            Key.USER_BIRTHDAY.setString(value.dateOfBirth.toString())
        }


    private enum class Key {
        BASIC_TOKEN, USER_EMAIL, USER_USERNAME, USER_NAME, USER_SURNAME, USER_BIRTHDAY;

        fun getBoolean(): Boolean? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getBoolean(name, false) else null
        fun getFloat(): Float? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getFloat(name, 0f) else null
        fun getInt(): Int? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getInt(name, 0) else null
        fun getLong(): Long? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getLong(name, 0) else null
        fun getString(): String? = if (sharedPreferences!!.contains(name)) sharedPreferences!!.getString(name, "") else null

        fun setBoolean(value: Boolean?) = value?.let { sharedPreferences!!.edit { putBoolean(name, value) } } ?: remove()
        fun setFloat(value: Float?) = value?.let { sharedPreferences!!.edit { putFloat(name, value) } } ?: remove()
        fun setInt(value: Int?) = value?.let { sharedPreferences!!.edit { putInt(name, value) } } ?: remove()
        fun setLong(value: Long?) = value?.let { sharedPreferences!!.edit { putLong(name, value) } } ?: remove()
        fun setString(value: String?) = value?.let { sharedPreferences!!.edit { putString(name, value) } } ?: remove()

        fun remove() = sharedPreferences!!.edit { remove(name) }
    }

}