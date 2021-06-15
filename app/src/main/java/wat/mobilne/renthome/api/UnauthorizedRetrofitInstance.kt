package wat.mobilne.renthome.api

import com.wat.rentahome.api.BasicApi
import wat.mobilne.renthome.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UnauthorizedRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BasicApi by lazy {
        retrofit.create(BasicApi::class.java)
    }
}