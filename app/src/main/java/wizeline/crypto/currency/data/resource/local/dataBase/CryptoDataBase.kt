package wizeline.crypto.currency.data.resource.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import wizeline.crypto.currency.data.resource.local.dao.AvailableBooksDao
import wizeline.crypto.currency.data.resource.local.dao.InformationTradingDao
import wizeline.crypto.currency.data.resource.local.dao.OrderBookDao
import wizeline.crypto.currency.data.resource.local.entities.AvailableBooksEntity
import wizeline.crypto.currency.data.resource.local.entities.*

@Database(
    entities = [AvailableBooksEntity::class,TickerEntity::class,BidsEntity::class,AsksEntity::class ],
    version = 1
)
abstract class CryptoDataBase:RoomDatabase() {

    abstract fun availableBook():AvailableBooksDao

    abstract fun informationTrading(): InformationTradingDao

    abstract fun orderBook(): OrderBookDao

}