package BaseDeDatos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exchangeRates: List<Entidad>)

    @Query("SELECT * FROM Divisas WHERE date = :date")
    suspend fun getExchangeRatesByDate(date: Long): List<Entidad>
}