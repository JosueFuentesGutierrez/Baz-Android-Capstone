package wizeline.crypto.currency.data.resource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import wizeline.crypto.currency.domain.model.AvailableBooksModel

@Entity
data class AvailableBooksEntity (
    @PrimaryKey
    val book: String,
    val minimumAmount: String,
    val maximumAmount: String,
    val minimumPrice:  String,
    val maximumPrice:  String,
    val minimumValue:  String,
)

fun AvailableBooksEntity.toBookModel():AvailableBooksModel{
    return AvailableBooksModel(
        book = book,
        minimumAmount = minimumAmount,
        maximumAmount = maximumAmount,
        minimumPrice  = minimumPrice,
        maximumPrice  = maximumPrice,
        minimumValue  = minimumValue
    )
}
