package com.example.monprofil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable

fun Serie(id: String?, viewModel: MainViewModel) {

    val serie by viewModel.serie.collectAsState()
    if (id != null) viewModel.serieInfo(id)

    LazyColumn() {

        item {
            TitreSerie(serie)
            Spacer(modifier = Modifier.height(5.dp))
            Spacer(modifier = Modifier.height(9.dp))
            Card(
                elevation = CardDefaults.cardElevation(3.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(
                        20.dp
                    )
                    .fillMaxWidth()
            ) {
                Row() {
                    Spacer(modifier = Modifier.width(30.dp))
                    SmallPosterSerie(serie)
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(40.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        DateSerie(serie)
                        Spacer(modifier = Modifier.height(20.dp))
                        SaisonSerie(serie)
                    }

                }
            }
            TextSynopSerie(serie)
            Spacer(modifier = Modifier.height(9.dp))
            ActeursSerie(serie)

        }
    }
}

@Composable
fun TitreSerie(serie: Series?) {

    if (serie != null) {
        Text(
            text = serie.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PosterSerie(serie: Series?) {

    if (serie != null) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w1280/" + serie.backdrop_path,
            contentDescription = null,
            //modifier = Modifier.fillMaxWidth()

        )
    }
}

@Composable
fun SmallPosterSerie(serie: Series?) {

    if (serie != null) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/" + serie.poster_path,
            contentDescription = "Ma super image"
        )
    }
}

@Composable
fun DateSerie(serie: Series?) {

    if (serie != null) {
        Text(
            text = serie.first_air_date,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Italic
            // modifier =Modifier.padding(horizontal = 0.dp)
        )
    }
}


@Composable
fun SaisonSerie(serie: Series?) {

    if (serie != null) {
        LazyColumn(modifier = Modifier.height(100.dp)) {
            items(serie.seasons) { saison ->
                Text(
                    text = saison.name,
                    //modifier = Modifier.padding(1.dp),
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }
}


@Composable
fun TextSynopSerie(serie: Series?) {

    if (serie != null) {

        Text(
            text = "Synopsis",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF800080)
        )
        Text(
            text = serie.overview,
            modifier = Modifier.padding(6.dp),
        )
    }
}


@Composable
fun ActeursSerie(serie: Series?) {
    if (serie != null) {
        Text(
            text = "Cast",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .height(500.dp)
        ) {
            items(serie.credits.cast) { actor ->
                MyItem(
                    navigateTo = { /*TODO*/ },
                    imagePath = "https://image.tmdb.org/t/p/w500/${actor.profile_path}",
                    title = actor.name,
                    date = actor.original_name
                )
            }
        }


    }
}


@Composable
fun ActeursListSerie(actor: CastTv) {

    Spacer(modifier = Modifier.height(10.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/" + actor.profile_path,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = actor.name,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = actor.character,
            fontWeight = FontWeight.W300
        )
    }


}

