package wizeline.crypto.currency.ui.informationTrading

import wizeline.crypto.currency.domain.model.OrderBookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel

data class TradingState(
    val information: TradingInformationModel = TradingInformationModel("","","","","","","","",""),
    val orderBook: OrderBookModel = OrderBookModel(),
    val error:  String="",
    val isLoading: Boolean = false
)