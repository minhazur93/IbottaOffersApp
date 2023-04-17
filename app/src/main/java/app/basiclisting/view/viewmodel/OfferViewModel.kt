package app.basiclisting.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.basiclisting.data.entities.Offer
import app.basiclisting.data.repo.OfferRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(
    private val offerRepo: OfferRepository
) : ViewModel() {

    var offers: LiveData<List<Offer>> = MutableLiveData()

    fun saveOffersInfo(jsonFileString: String?) {
        val listPersonType = object : TypeToken<List<Offer>>() {}.type
        val offers: List<Offer> = Gson().fromJson(jsonFileString, listPersonType)
        insertOffers(offers)
    }

    private fun insertOffers(offer: List<Offer>) {
        viewModelScope.launch {
            offerRepo.insertOffers(offer)
        }
    }

    fun getAllOffers() {
        offers = offerRepo.getAllOffers()
    }

    suspend fun updateStatus(value: Int, id: String) {
        offerRepo.updateStatus(value, id)
    }

}