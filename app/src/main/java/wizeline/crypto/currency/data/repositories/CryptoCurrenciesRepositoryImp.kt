package wizeline.crypto.currency.data.repositories

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import wizeline.crypto.currency.data.resource.remote.CryptoCurrenciesApi
import wizeline.crypto.currency.domain.repositories.CryptoCurrenciesRepository
import wizeline.crypto.currency.data.models.convertToTradingInformation
import wizeline.crypto.currency.data.models.toListOrderBook
import wizeline.crypto.currency.data.resource.local.entities.toBookModel
import wizeline.crypto.currency.data.Result
import wizeline.crypto.currency.data.models.convertToListBooks
import wizeline.crypto.currency.data.resource.local.dao.AvailableBooksDao
import wizeline.crypto.currency.data.resource.local.dao.InformationTradingDao
import wizeline.crypto.currency.data.resource.local.dao.OrderBookDao
import wizeline.crypto.currency.data.resource.local.entities.TickerEntity
import wizeline.crypto.currency.data.resource.local.entities.toAsksModel
import wizeline.crypto.currency.data.resource.local.entities.toInformationModel
import wizeline.crypto.currency.domain.model.*
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class CryptoCurrenciesRepositoryImp @Inject constructor(
    private val api: CryptoCurrenciesApi,
    private var availableBooksDao: AvailableBooksDao,
    private var informationTradingDao: InformationTradingDao,
    private var orderBookDao: OrderBookDao
) : CryptoCurrenciesRepository {

    override suspend fun getAvailableBooks(): Flow<Result<List<AvailableBooksModel>>> = flow {
        emit(Result.Loading())

        try {
            val availableBooks = api.getAvailableBooks().convertToListBooks()
            val availableBooksEntity = availableBooks.map { it.toBookEntity() }
            availableBooksDao.delete()
            availableBooksDao.insertAll(availableBooksEntity)
            emit(
                Result.Success(
                    data = availableBooks
                )
            )

        } catch (e: HttpException) {
            val data = availableBooksDao.getAll()
            val data2= data.map {
                it.toBookModel()
            }
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data=data2
                ))

        }catch (e: IOException) {
            val data = availableBooksDao.getAll()
            val data2= data.map {
                it.toBookModel()
            }
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data=data2
                ))
        }catch (e: Exception){
            Log.e("ErrorJosue", e.toString())
        }
    }

    override suspend fun getInformationTrading(book: String): Flow<Result<TradingInformationModel>>  = flow {

        emit(
            Result.Loading()
        )
        try {

            val information= api.getInformationTrading(book).convertToTradingInformation()
            val informationEntity= information.toInformationEntity()
            informationTradingDao.insertInformation(informationEntity)
            emit(
                Result.Success(
                    data = information
                )
            )
        } catch (e: HttpException) {
            val data=informationTradingDao.getInformation(book) ?: TickerEntity("","","","","","","","","")
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data = data.toInformationModel()
                ))
        }catch (e: IOException) {
            val data=informationTradingDao.getInformation(book) ?: TickerEntity("","","","","","","","","")
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data=data.toInformationModel()
                ))
        }

    }

    override suspend fun getOrderBook(book: String): Flow<Result<OrderBookModel>>  = flow {
        emit(
            Result.Loading()
        )
        try {
            val orderBook= api.getOrderBook(book).toListOrderBook()
            orderBookDao.deleteAsks(book)
            orderBookDao.deleteBids(book)
            orderBook.asks?.let { orderBookDao.insertAsks(it.map {asks-> asks.toAsksEntity() }) }
            orderBook.bids?.let { orderBookDao.insertBids(it.map {binds-> binds.toABidsEntity() }) }
            emit(
                Result.Success(
                    data = orderBook
                )
            )
        } catch (e: HttpException) {
            var asks =orderBookDao.getAsks(book).map { asks-> asks.toAsksModel()  }
            var binds =orderBookDao.getBids(book).map { asks-> asks.toAsksModel()  }
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data= OrderBookModel(asks, binds)
                ))

        }catch (e: IOException) {
            var asks =orderBookDao.getAsks(book).map { asks-> asks.toAsksModel()  }
            var binds =orderBookDao.getBids(book).map { asks-> asks.toAsksModel()  }
            emit(
                Result.Error(
                    message = "Opps sin conexión ",
                    data= OrderBookModel(asks, binds)
                ))
        }
    }



    suspend fun getInformationTradingRXJ(book:String):TradingInformationModel {
        return suspendCoroutine {
            { error: Any ->}
            api.getInformationTradingRXJ(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }

        }
    }





}
