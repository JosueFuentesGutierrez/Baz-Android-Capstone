package wizeline.crypto.currency.domain.useCase

import kotlinx.coroutines.flow.Flow
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.domain.model.OrderBookModel
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import javax.inject.Inject

class OrderBookUseCase @Inject constructor(
    private var repository:CryptoCurrenciesRepository
) {
    suspend operator fun invoke(book:String): Flow<Result<OrderBookModel>> {
        return repository.getOrderBook(book)
    }
}