package wizeline.crypto.currency.domain.model

import wizeline.crypto.currency.data.resource.local.entities.AvailableBooksEntity

data class AvailableBooksModel(
    val book: String,
    val minimumAmount: String,
    val maximumAmount: String,
    val minimumPrice: String,
    val maximumPrice: String,
    val minimumValue: String,
)

fun AvailableBooksModel.toBookEntity(): AvailableBooksEntity {

    return AvailableBooksEntity(
        book = book,
        minimumAmount = minimumAmount,
        maximumAmount = maximumAmount,
        minimumPrice = minimumPrice,
        maximumPrice = maximumPrice,
        minimumValue = minimumValue
    )

}