package wizeline.crypto.currency.domain.model

data class OrderBookModel(
    val asks: List<AsksBidsModel> ? = null,
    val bids: List<AsksBidsModel> ? = null
)