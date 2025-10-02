package perso.lunabee.gamevault.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// Interface DAO pour les opérations CRUD sur la base de données
@Dao
interface GameDao {

    // Méthode pour insérer un jeu dans la base de données
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: GameEntity)

    // Méthode pour mettre à jour un jeu dans la base de données
    @Update
    suspend fun update(game: GameEntity)

    // Méthode pour supprimer un jeu de la base de données
    @Delete
    suspend fun delete(game: GameEntity)

    // Méthode pour récupérer tous les jeux de la base de données
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

}