package wizeline.crypto.currency.data.resource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import wizeline.crypto.currency.domain.model.TradingInformationModel

@Entity
data class TickerEntity(
    @PrimaryKey
    val book: String,
    val volume: String?=null,
    val high: String?=null,
    val last: String?=null,
    val low: String?=null,
    val wap: String?=null,
    val ask: String?=null,
    val bid: String?=null,
    val createdAt: String?=null
)


fun TickerEntity.toInformationModel(): TradingInformationModel {
    return TradingInformationModel(
        book = book,
        volume = volume?:"",
        high = high?:"",
        last = last?:"",
        low = low?:"",
        wap = wap?:"",
        ask = ask?:"",
        bid = bid?:"",
        createdAt = createdAt?:""
    )
}