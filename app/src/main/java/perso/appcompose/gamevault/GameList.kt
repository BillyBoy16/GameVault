package perso.appcompose.gamevault

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import perso.appcompose.gamevault.data.GameEntity
import perso.appcompose.gamevault.R

// Fonction composant la liste des jeux
@Composable
fun GameList(
    modifier: Modifier,
    gameList: List<GameEntity>,
    viewModel: GameViewModel = hiltViewModel()
){
    val openDialog = remember { mutableStateOf(false) }
    val selectedGame = remember { mutableStateOf<GameEntity?>(null) }
    // Affichage de la liste des jeux
    LazyColumn(modifier = Modifier) {
        item{
            GameEntry(modifier = Modifier.padding(8.dp))
        }
        items(items = gameList){ game ->
            GameCard(game = game, onClick = {
                selectedGame.value = game
               openDialog.value = true})
        }
    }

    // Affichage du dialogue de détails du jeu
    if (openDialog.value) {
            selectedGame.value?.let { game ->
                GameDetailsDialog(game = game, onDismiss = {
                    openDialog.value = false
                },
                    onDelete = { gameToDelete ->
                        viewModel.deleteGame(gameToDelete)
                    })
            }
    }
}

// Fonction composant représentant la carte d'un jeu
@Composable
fun GameCard(game: GameEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RectangleShape,
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Affichage de l'image du jeu et des attributs du jeu
            Image(
                painter = painterResource(R.drawable.ic_app),
                contentDescription = "Icône de jeu par défaut",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = game.title,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = game.genre,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = game.platform,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Sortie : ${game.releaseYear}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}