package wizeline.crypto.currency.data.models

import wizeline.crypto.currency.domain.model.OrderBookModel
import wizeline.crypto.currency.domain.model.AsksBidsModel


data class OrderBookDto(
    val success: Boolean,
    val payload: PayLoadOrderBookDto
)

fun OrderBookDto.toListOrderBook(): OrderBookModel{
    val askList = payload.asks?.map {
        AsksBidsModel(
            amount = it.amount,
            book = it.book,
            price = it.price
        )
    }?.toList()

    val bidList = payload.bids?.map {
        AsksBidsModel(
            amount = it.amount,
            book = it.book,
            price = it.price
        )
    }?.toList()

    return OrderBookModel(
        asks = askList ?: emptyList(),
        bids = bidList ?: emptyList()
    )
}