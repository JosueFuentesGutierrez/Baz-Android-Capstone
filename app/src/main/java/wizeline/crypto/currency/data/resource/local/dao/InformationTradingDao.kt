package wizeline.crypto.currency.data.resource.local.dao

import androidx.room.*
import wizeline.crypto.currency.data.resource.local.entities.TickerEntity

@Dao
interface InformationTradingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInformation(information:TickerEntity)

    @Query("SELECT * FROM tickerEntity WHERE book = :book")
    suspend fun getInformation(book:String): TickerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInformationRXJ(information:TickerEntity)
}