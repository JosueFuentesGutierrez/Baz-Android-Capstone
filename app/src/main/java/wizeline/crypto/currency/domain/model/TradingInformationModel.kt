package wizeline.crypto.currency.domain.model

import wizeline.crypto.currency.data.resource.local.entities.TickerEntity

data class TradingInformationModel(
    val book: String="",
    val volume: String="",
    val high: String="",
    val last: String="",
    val low: String="",
    val wap: String="",
    val ask: String="",
    val bid: String="",
    val createdAt: String=""
)

fun TradingInformationModel.toInformationEntity(): TickerEntity {

    return TickerEntity(
        book = book,
        volume = volume,
        high = high,
        last = last,
        low = low,
        wap = wap,
        ask = ask,
        bid = bid,
        createdAt = createdAt
    )

}