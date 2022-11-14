package wizeline.crypto.currency.data.resource.local.dao

import androidx.room.*
import wizeline.crypto.currency.data.resource.local.entities.AsksEntity
import wizeline.crypto.currency.data.resource.local.entities.BidsEntity

@Dao
interface OrderBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsks(Asks: List<AsksEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBids(Bids: List<BidsEntity>)

    @Query("DELETE FROM AsksEntity WHERE book = :book")
    suspend fun deleteAsks(book:String)

    @Query("DELETE FROM BidsEntity WHERE book = :book")
    suspend fun deleteBids(book:String)

    @Query("SELECT * FROM AsksEntity WHERE book = :book")
    suspend fun getAsks(book:String): List<AsksEntity>

    @Query("SELECT * FROM BidsEntity WHERE book = :book")
    suspend fun getBids(book:String): List<BidsEntity>

}