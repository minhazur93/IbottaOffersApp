package app.basiclisting.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.basiclisting.data.dao.OfferDao
import app.basiclisting.data.entities.Offer

@Database(entities = [Offer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao
}