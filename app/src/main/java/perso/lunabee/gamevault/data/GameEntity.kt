package perso.lunabee.gamevault.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entité représentant un jeu de la base de données
@Entity(tableName = "games")
data class GameEntity(
    // Clé primaire auto-incrémentée
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // Colonnes de la table
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "genre")val genre: String,
    @ColumnInfo(name = "platform")val platform: String,
    @ColumnInfo(name = "releaseYear")val releaseYear: Int,
    @ColumnInfo(name = "completed")val completed: Int
)