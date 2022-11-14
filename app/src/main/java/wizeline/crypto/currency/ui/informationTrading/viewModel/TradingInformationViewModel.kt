package wizeline.crypto.currency.ui.informationTrading.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.domain.model.OrderBookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel
import wizeline.crypto.currency.domain.useCase.OrderBookUseCase
import wizeline.crypto.currency.domain.useCase.TradingInformationUseCase
import wizeline.crypto.currency.ui.informationTrading.TradingState

@HiltViewModel
class TradingInformationViewModel @Inject constructor(
    private val tradingInformationUseCase: TradingInformationUseCase,
    private val orderBookUseCase: OrderBookUseCase
) : ViewModel() {
    val state = MutableLiveData(TradingState(isLoading = true))

    fun getOrderBook(book:String){
        viewModelScope.launch {
            orderBookUseCase(book).onEach { result->
                when (result) {
                    is Result.Success -> {
                        state.value = state.value?.copy(
                            orderBook = result.data?: OrderBookModel(),
                            error = "",
                            isLoading = false)
                    }
                    is Result.Error -> {
                        state.value = state.value?.copy(
                            orderBook = result.data?: OrderBookModel(),
                            error = result?.message?:"Error inesperado, revisa tu conexión y vuelve a intentar",
                            isLoading = false)

                    }
                    is Result.Loading->{
                        state.value= state.value?.copy(
                            isLoading = true,
                            error = "",
                        )
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    fun getTradingInformation(book:String) {
        viewModelScope.launch {
            tradingInformationUseCase(book).onEach { result ->
                when (result) {
                    is Result.Success -> {
                            state.value = state.value?.copy(
                                information = result.data?: TradingInformationModel(),
                                error = "",
                                isLoading = false)

                    }
                    is Result.Error -> {
                        state.value= state.value?.copy(
                            information = result.data?: TradingInformationModel(),
                            error = result.message?:"Error inesperado, revisa tu conexión y vuelve a intentar",
                            isLoading = false
                        )
                    }
                    is Result.Loading->{
                        state.value= state.value?.copy(
                            isLoading = true,
                            error = ""
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}