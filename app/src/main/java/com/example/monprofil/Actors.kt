package com.example.monprofil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Actors(navController: NavController, viewModel: MainViewModel) {
    val actors by viewModel.actors.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxSize(),

        ) {
        items(actors) { actor ->
            MyItem(
                navigateTo = { /*TODO*/ },
                imagePath = "https://image.tmdb.org/t/p/w300/"+ actor.profile_path,
                title = actor.name,
                date = actor.known_for_department
            )
        }
    }
}