package wizeline.crypto.currency.data.resource.local.dao

import androidx.room.*
import wizeline.crypto.currency.data.resource.local.entities.AvailableBooksEntity

@Dao
interface AvailableBooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<AvailableBooksEntity>)

    @Query("DELETE FROM availableBooksEntity")
    suspend fun delete()

    @Query("SELECT * FROM availableBooksEntity")
    suspend fun getAll(): List<AvailableBooksEntity>
}