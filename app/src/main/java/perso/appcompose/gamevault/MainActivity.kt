package perso.appcompose.gamevault

import android.app.Application
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import perso.appcompose.gamevault.ui.theme.GameVaultTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import perso.appcompose.gamevault.R

@HiltAndroidApp
class GameVaultApp : Application()
    @AndroidEntryPoint
    class MainActivity : ComponentActivity() {
        // Initialisation de l'activité
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // Initialisation de Hilt
            setContent {
                GameVaultTheme {
                    // Affichage de l'interface utilisateur
                    Scaffold() { paddingValues ->
                        Column(modifier = Modifier
                            .padding(paddingValues)
                        ) {
                            Surface(color = MaterialTheme.colorScheme.background) {
                                WelcomeMessageText()
                            }
                            DisplayGameList()
                        }
                    }
                }
            }
        }
    }

    @Preview(
        name = "Light Mode",
        showBackground = true,
        backgroundColor = 0xFFFFFFFF
    )
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode",
        backgroundColor = 0xFF000000,
    )

    // Fonction composant le texte de bienvenue
    @Composable
    fun WelcomeMessageText() {
        val textColor = MaterialTheme.colorScheme.onBackground
        // Affichage du texte de bienvenue
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.welcome_message),
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    letterSpacing = 1.5.sp,
                    lineHeight = 36.sp
                )
            )
        }
    }
// Fonction composant la liste des jeux
@Composable
fun DisplayGameList(viewModel: GameViewModel = hiltViewModel()){
    val gameList by viewModel.gameList.collectAsState(initial = emptyList())
    GameList(modifier = Modifier, gameList = gameList)
    }