package wizeline.crypto.currency.ui.homeCurrency

import wizeline.crypto.currency.domain.model.AvailableBooksModel

data class HomeState(
    val book :  List<AvailableBooksModel> = emptyList(),
    val error:  String="",
    val isLoading: Boolean = false
)