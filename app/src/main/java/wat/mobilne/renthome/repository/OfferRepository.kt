package wat.mobilne.renthome.repository

import retrofit2.Response
import wat.mobilne.renthome.api.RetrofitInstance
import wat.mobilne.renthome.models.Offer

class OfferRepository {
    suspend fun createOffer(offer: Offer): Response<Offer> {
        return RetrofitInstance.api.createOffer(offer)
    }
    suspend fun getOffers(): Response<List<Offer>> {
        return RetrofitInstance.api.getOffers()
    }
}