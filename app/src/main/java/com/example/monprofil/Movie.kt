package com.example.monprofil


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImage

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun Movie(id: String?, viewModel: MainViewModel) {

    val movie by viewModel.movie.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    if (id != null) viewModel.movieDetails(id)
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                Modifier.align(Alignment.Center)
            )
        }
    }
    LazyColumn(
        Modifier.padding(20.dp)
    ) {

        item {
            TitreFilm(movie)
            Spacer(modifier = Modifier.height(5.dp))
            Spacer(modifier = Modifier.height(9.dp))
            Card (
                elevation = CardDefaults.cardElevation(3.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
                ,
                modifier = Modifier
                    .padding(
                        20.dp
                    )
                    .fillMaxWidth()
            ){
                Row() {
                    Spacer(modifier = Modifier.width(30.dp))
                    SmallPoster(movie)
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.width(40.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        DateFilm(movie)
                        Spacer(modifier = Modifier.height(20.dp))
                        GenreMovie(movie)

                    }
                }
            }

            TextSynop(movie)
            Spacer(modifier = Modifier.height(9.dp))
            Acteurs(movie)

        }
    }
}

@Composable
fun TitreFilm(movie: Movie?) {

    if (movie != null) {
        Text(
            text = movie.original_title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            color = Color(0xFF800080)
        )
    }
}

@Composable
fun PosterFilm(movie: Movie?) {

    if (movie != null) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w1280/" + movie.backdrop_path,
            contentDescription = null,
            //modifier = Modifier.fillMaxWidth()

        )
    }
}

@Composable
fun SmallPoster(movie: Movie?) {

    if (movie != null) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/" + movie.poster_path,
            contentDescription = "Ma super image"
        )
    }
}

@Composable
fun DateFilm(movie: Movie?) {

    if (movie != null) {
        Text(
            text = movie.release_date,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Italic
            // modifier =Modifier.padding(horizontal = 0.dp)
        )
    }
}


@Composable
fun GenreMovie(movie: Movie?) {

    if (movie != null) {
        LazyColumn(modifier = Modifier.height(70.dp)) {
            items(movie.genres) { genre ->
                Text(
                    text = genre.name,
                    //modifier = Modifier.padding(1.dp),
                    fontStyle = FontStyle.Italic
                )
            }
        }

    }
}


@Composable
fun TextSynop(movie: Movie?) {

    if (movie != null) {

        Text(
            text = "Synopsis",
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF800080)
        )
        Text(
            text = movie.overview,
            modifier = Modifier.padding(6.dp)
        )
    }
}

@Composable
fun Acteurs(movie: Movie?) {
    if (movie != null) {
        Text(
            text = "Cast",
            style = MaterialTheme.typography.bodyLarge,
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
            items(movie.credits.cast) { actor ->
                MyItem(
                    navigateTo = { /*TODO*/ },
                    imagePath ="https://image.tmdb.org/t/p/w500/"  + actor.profile_path,
                    title = actor.name,
                    date = actor.original_name
                )

            }
        }


    }
}


@Composable
fun ActeursList(actor: CastM) {

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

