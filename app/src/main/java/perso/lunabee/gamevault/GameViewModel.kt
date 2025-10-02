package perso.lunabee.gamevault

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import perso.lunabee.gamevault.data.GameEntity
import perso.lunabee.gamevault.data.GameRepository
import javax.inject.Inject



@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {
    // Liste des jeux
    private val _games = mutableStateOf<List<GameEntity>>(emptyList())

    val gameList get() = gameRepository.getGamesList()

    init {
        loadGames()
    }

    // Chargement des jeux depuis la base de données
    private fun loadGames() {
        viewModelScope.launch {
            gameRepository.getGamesList().collect { allGames ->
                _games.value = allGames
                Log.d("GameVault", "Jeux dans la base de données : $allGames")
            }
        }
    }

    // State du formulaire de création de jeu
    var gameUiState by mutableStateOf(GameEntity(id = 0, title = "", platform = "", genre = "", releaseYear = 0, completed = 0))
        private set

    // Mise à jour du state du formulaire de création de jeu
    fun updateGameUiState(gameEntity: GameEntity) {
        gameUiState = gameEntity
    }

    // Ajout d'un nouveau jeu à la base de données
    fun insertGame() {
        viewModelScope.launch {
            gameRepository.insertGame(gameUiState)
            loadGames()
        }
    }

    // Réinitialisation du state du formulaire de création de jeu
    fun resetGameUiState() {
        updateGameUiState(gameUiState)
    }

    // Suppression d'un jeu de la base de données
    fun deleteGame(game: GameEntity) {
        viewModelScope.launch {
            gameRepository.deleteGame(game)
        }
    }
}



