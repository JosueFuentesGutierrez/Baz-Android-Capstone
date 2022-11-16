package wizeline.crypto.currency.domain.repositories

import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import wizeline.crypto.currency.domain.model.AvailableBooksModel
import wizeline.crypto.currency.domain.model.OrderBookModel
import wizeline.crypto.currency.domain.model.TradingInformationModel
import wizeline.crypto.currency.data.Result

interface CryptoCurrenciesRepository {

    suspend fun getAvailableBooks(): Flow<Result<List<AvailableBooksModel>>>

    suspend fun getInformationTrading(book:String): Flow<Result<TradingInformationModel>>

    suspend fun getOrderBook(book:String): Flow<Result<OrderBookModel>>

    fun getInformationTradingRXJ(book:String): Single<TradingInformationModel>

}