package perso.lunabee.gamevault.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(private val gameDao: GameDao) {
    // Méthodes pour interagir avec la base de données

    // Méthode pour insérer un jeu dans la base de données
    suspend fun insertGame(gameEntity: GameEntity) = gameDao.insert(gameEntity)

    // Méthode pour mettre à jour un jeu dans la base de données
    suspend fun updateGame(gameEntity: GameEntity) = gameDao.update(gameEntity)

    // Méthode pour supprimer un jeu de la base de données
    suspend fun deleteGame(gameEntity: GameEntity) = gameDao.delete(gameEntity)

    // Méthode pour récupérer tous les jeux de la base de données
    fun getGamesList(): Flow<List<GameEntity>> = gameDao.getAllGames()


}