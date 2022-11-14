package wizeline.crypto.currency.domain.model

import wizeline.crypto.currency.data.resource.local.entities.AsksEntity
import wizeline.crypto.currency.data.resource.local.entities.BidsEntity

data class AsksBidsModel(
    val book: String,
    val price: String,
    val amount: String
)

fun AsksBidsModel.toAsksEntity(): AsksEntity {
    return AsksEntity(
        book = book,
        price = price,
        amount = amount
    )
}

fun AsksBidsModel.toABidsEntity(): BidsEntity {
    return BidsEntity(
        book = book,
        price = price,
        amount = amount
    )
}

