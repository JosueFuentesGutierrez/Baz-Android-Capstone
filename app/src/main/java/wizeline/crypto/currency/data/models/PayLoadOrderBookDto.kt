package wizeline.crypto.currency.data.models


data class PayLoadOrderBookDto(
    val asks: List<asksBidsDto>,
    val bids: List<asksBidsDto>,
    val updated_at: String,
    val sequence:String
)