package wizeline.crypto.currency.data.resource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import wizeline.crypto.currency.domain.model.AsksBidsModel
import wizeline.crypto.currency.domain.model.TradingInformationModel

@Entity
data class AsksEntity(
    @PrimaryKey(autoGenerate = true)
    val idAsk:Int=0,
    val book: String,
    val price: String,
    val amount: String
)

fun AsksEntity.toAsksModel():AsksBidsModel {
    return AsksBidsModel(
        book = book,
        price = price?:"",
        amount = amount?:"",
    )
}