package BaseDeDatos



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Entidad::class], version = 2, exportSchema = false)
abstract class InicioBD : RoomDatabase() {

    abstract fun exchangeRateDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: InicioBD? = null

        fun getDatabase(context: Context): InicioBD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    InicioBD::class.java,
                    "DivisasDB"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
