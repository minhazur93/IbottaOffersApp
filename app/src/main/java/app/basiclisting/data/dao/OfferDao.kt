package app.basiclisting.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.basiclisting.data.entities.Offer

@Dao
interface OfferDao {

    @Query("SELECT * FROM offer")
    fun getAllOffers(): LiveData<List<Offer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffer(offer: List<Offer>)

    @Query("UPDATE offer SET isFavourite = :value where id = :offerId")
    fun updateStatus(value: Int, offerId: String)
}