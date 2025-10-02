package perso.lunabee.gamevault.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Classe de base de données Room pour les jeux
@Database(entities = [GameEntity::class], version = 1)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
    // Création d'une instance unique de la base de données
    companion object {
        @Volatile
        private var Instance : GameDatabase? = null
        // Méthode pour obtenir l'instance de la base de données
        fun getDatabase(context: Context): GameDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GameDatabase::class.java, "game_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}