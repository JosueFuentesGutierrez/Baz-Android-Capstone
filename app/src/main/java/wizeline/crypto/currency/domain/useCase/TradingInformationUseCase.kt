package wizeline.crypto.currency.domain.useCase

import io.reactivex.Single
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

    fun invokeRXJ(book:String):Single<TradingInformationModel>{
        return repository.getInformationTradingRXJ(book)
    }
}