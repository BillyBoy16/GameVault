package perso.lunabee.gamevault.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

// Fichier de configuration de Hilt
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase {
        // Création de la base de données
        val database = Room.databaseBuilder(context, GameDatabase::class.java, "game_database")
            .fallbackToDestructiveMigration()
            .build()

        Log.d("GameVault", "GameDatabase créée : $database")

        return database
    }

    // Fournit une instance de GameDao
    @Provides
    fun provideGameDao(database: GameDatabase): GameDao {
        return database.gameDao()
    }

    // Fournit une instance de GameRepository
    @Provides
    fun provideGameRepository(gameDao: GameDao): GameRepository {
        return GameRepository(gameDao)
    }
}
