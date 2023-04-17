package app.basiclisting.data.repo

import app.basiclisting.data.dao.OfferDao
import app.basiclisting.data.entities.Offer
import javax.inject.Inject

class OfferRepository @Inject constructor(private val offerDao: OfferDao) {

    suspend fun insertOffers(offer: List<Offer>) {
        offerDao.insertOffer(offer)
    }
    fun getAllOffers() = offerDao.getAllOffers()

    fun updateStatus(value: Int,id : String) {
        offerDao.updateStatus(value,id)
    }
}