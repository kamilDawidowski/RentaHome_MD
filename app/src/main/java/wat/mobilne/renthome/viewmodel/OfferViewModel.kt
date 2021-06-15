package wat.mobilne.renthome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import wat.mobilne.renthome.models.Offer
import wat.mobilne.renthome.repository.OfferRepository

class OfferViewModel : ViewModel(){
    val offerRepository : OfferRepository = OfferRepository()

    val getOffersResponse: MutableLiveData<Response<List<Offer>>> = MutableLiveData()
    val createOfferResponse: MutableLiveData<Response<Offer>> = MutableLiveData()


    fun createOffer(offer: Offer) {
        viewModelScope.launch {
            val response = offerRepository.createOffer(offer)
            createOfferResponse.value = response
            getOffers()
        }
    }
    fun getOffers() {
        viewModelScope.launch {
            val response = offerRepository.getOffers()
            getOffersResponse.value = response
        }
    }


}