package wizeline.crypto.currency.data.resource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import wizeline.crypto.currency.domain.model.AsksBidsModel

@Entity
data class BidsEntity(
    @PrimaryKey(autoGenerate = true)
    val idBids:Int=0,
    val book: String,
    val price: String,
    val amount: String
)

fun BidsEntity.toAsksModel(): AsksBidsModel {
    return AsksBidsModel(
        book = book,
        price = price?:"",
        amount = amount?:"",
    )
}