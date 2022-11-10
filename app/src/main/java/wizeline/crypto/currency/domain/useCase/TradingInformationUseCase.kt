package wizeline.crypto.currency.domain.useCase

import kotlinx.coroutines.flow.*
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.domain.model.TradingInformationModel
import javax.inject.Inject

class TradingInformationUseCase @Inject constructor(
    private val repository: CryptoCurrenciesRepository
) {
    suspend operator fun invoke(book:String): Flow<Result<TradingInformationModel>>{
        return repository.getInformationTrading(book)
    }
}