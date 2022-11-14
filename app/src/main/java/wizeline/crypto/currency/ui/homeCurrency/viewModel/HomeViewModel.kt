package wizeline.crypto.currency.ui.homeCurrency.viewModel


import android.content.res.loader.ResourcesProvider
import android.content.res.loader.ResourcesProvider.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wizeline.crypto.currency.domain.useCase.AvailableBookUseCase
import wizeline.crypto.currency.ui.homeCurrency.HomeState
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import wizeline.crypto.currency.R
import wizeline.crypto.currency.data.Result
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val availableBookUseCase: AvailableBookUseCase
) : ViewModel() {
    val state = MutableLiveData(HomeState(isLoading = true))

    fun getAvailableBook(search: String) {
        viewModelScope.launch {

            availableBookUseCase(search).onEach { result ->
                when (result) {
                    is Result.Success -> {
                        state.value = state.value?.copy(
                            book = result.data ?: emptyList(),
                            error="",
                            isLoading = false
                        )
                    }
                    is Result.Error -> {
                        state.value = state.value?.copy(
                            isLoading = false,
                            book = result.data ?: emptyList(),
                            error    = result?.message?:"Error inesperado, revisa tu conexión y vuelve a intentar"
                        )
                    }
                    is Result.Loading -> {
                        state.value = state.value?.copy(
                            isLoading = true,
                            error    = ""
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}