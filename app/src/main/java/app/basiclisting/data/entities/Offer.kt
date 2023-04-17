package app.basiclisting.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "offer")
data class Offer(
    @PrimaryKey
    val id: String,
    val url: String?,
    val name: String,
    val description: String,
    val terms: String,
    val current_value: String,
    val isFavourite: Boolean
) : java.io.Serializable