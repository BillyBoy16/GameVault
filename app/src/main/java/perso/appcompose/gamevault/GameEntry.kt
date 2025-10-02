package perso.appcompose.gamevault

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import perso.appcompose.gamevault.data.GameEntity
import perso.appcompose.gamevault.R

@Composable
fun GameEntry(
    modifier: Modifier,
    viewModel: GameViewModel = hiltViewModel()
) {
    // State des champs de saisie
    val uiState = viewModel.gameUiState
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier.padding(all = 8.dp)) {
        // Champs de saisie pour chaque attribut du jeu
        OutlinedTextField(value = uiState.title,
            onValueChange = {viewModel.updateGameUiState(uiState.copy(title = it))},
            label = {Text(text = "Nom du jeu") }
        )

        OutlinedTextField(value = uiState.genre,
            onValueChange = {viewModel.updateGameUiState(uiState.copy(genre = it))},
            label = {Text(text = "Genre") }
        )

        OutlinedTextField(value = uiState.platform,
            onValueChange = {viewModel.updateGameUiState(uiState.copy(platform = it))},
            label = {Text(text = "Plateforme") }
        )

        OutlinedTextField(value = uiState.releaseYear.toString(),
            onValueChange = {
                val year = it.toIntOrNull() ?: 0
                viewModel.updateGameUiState(uiState.copy(releaseYear = year))
            },
            label = {Text(text = "Année de sortie") }
        )

        OutlinedTextField(value = uiState.completed.toString(),
            onValueChange = {
                val completed = it.toIntOrNull() ?: 0
                viewModel.updateGameUiState(uiState.copy(completed = completed))
            },
            label = {Text(text = "Pourcentage complété") }
        )
        // Bouton pour ajouter le jeu
        Button(
            onClick = {
                viewModel.insertGame()
                viewModel.resetGameUiState()
                Toast.makeText(context, "Jeu créé", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "Ajouter")
        }


    }
}

// Dialog pour afficher les détails du jeu
@Composable
fun GameDetailsDialog(game: GameEntity, onDismiss: () -> Unit, onDelete: (GameEntity) -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = game.title, style = MaterialTheme.typography.titleLarge)
        },
        text = {
            Column {
                Text("Plateforme : ${game.platform}")
                Text("Genre : ${game.genre}")
                Text("Année de sortie : ${game.releaseYear}")
                Text("Complété à ${game.completed} %")

                LinearProgressIndicator(
                    progress = {
                        game.completed / 100f
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                )
            }
        },
        // Bouton pour fermer la dialog
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.close_dialog)
                )
            }
        },
        // Bouton pour supprimer le jeu
        dismissButton = {
            TextButton(
                onClick = {
                    onDelete(game)
                    onDismiss()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete_game)
                )
            }
        }
    )
}