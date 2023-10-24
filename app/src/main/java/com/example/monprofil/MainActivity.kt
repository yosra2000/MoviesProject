package com.example.monprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.navigation.NavDestination.Companion.hierarchy


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()

        setContent {
            var selectedItem by  remember() {
                mutableIntStateOf(1)
            }
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route


            //pour le recherche
            var issearch by remember{mutableStateOf(false)}// à noter que mon issearch c'est mon type d'état (si je suis dans la loupe ou dan sle champs de saisie d
            // e recherche
            var text by remember{mutableStateOf("")}

            Scaffold(
                topBar = {
                    if (currentDestination!= "Screen") {
                        if(issearch==false){

                            TopAppBar(
                                title = {
                                    Text(text = "Search here.. ")
                                },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        if(currentDestination == "movies"){
                                            navController.navigate("screen")
                                        }else {
                                            navController.navigate("movies")
                                        }
                                    })

                                    {
                                        Icon(
                                            painter = painterResource(id = R.drawable.loupe),
                                            contentDescription = null
                                        )
                                    }
                                },
                                actions = {
                                    // RowScope here, so these icons will be placed horizontally
                                    IconButton(onClick = {
                                        issearch=true
                                    }) {
                                        Icon(painter = painterResource(id = R.drawable.loupe),
                                            contentDescription = null)
                                    }

                                },



                            )

                            //etat ou je clique dessus et le champs de search s'affiche
                        }else{
                            TopAppBar(
                                title = {
                                    Text(text = "Search here.. ")

                                },

                                navigationIcon = {
                                    IconButton(onClick = {
                                        if(currentDestination == "Film"){
                                            navController.navigate("screen")
                                        }else {
                                            navController.navigate("Film")
                                        }
                                    })


                                    {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_movie_24),
                                            contentDescription = null
                                        )
                                    }
                                },

                                actions = {
                                    // RowScope here, so these icons will be placed horizontally

                                    Surface(
                                        modifier= Modifier
                                            .fillMaxWidth()
                                            .height(56.dp),
                                        color=MaterialTheme.colorScheme.primary
                                    ){
                                        TextField(
                                            modifier=Modifier.fillMaxWidth(),
                                            value = text,
                                            placeholder = { Text(text = "Search here...")},

                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Text,
                                                imeAction = ImeAction.Search
                                            ),
                                            keyboardActions = KeyboardActions(onSearch={
                                                viewModel.SearchKeyWord(text)
                                                viewModel.SeachSeries(text)
                                                viewModel.SeachActors(text)
                                            }),
                                            onValueChange = {
                                                text = it
                                            }

                                        )
                                    }

                                },


                                )
                        }
                    }

                },

                bottomBar = {
                    if (currentDestination != "Screen") {
                        BottomAppBar(

                            actions = {
                                Row(
                                    Modifier
                                        .background(
                                            color = Color(0xFF800080)
                                        )
                                        .fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceEvenly

                                ) {
                                    IconButton(
                                        onClick = {
                                            navController.navigate("movies")
                                            selectedItem = 1
                                        }) {
                                        Icon(
                                            Icons.Filled.Menu,
                                            contentDescription = "Go to Movies",
                                            tint = if (selectedItem == 1) Color.White else Color.Gray

                                        )
                                    }
                                    IconButton(onClick = {
                                        navController.navigate("series")
                                        selectedItem = 2
                                    }
                                    ) {
                                        Icon(
                                            Icons.Filled.MoreVert,
                                            contentDescription = "Go to TVs",
                                            tint = if (selectedItem == 2) Color.White else Color.Gray

                                        )
                                    }
                                    IconButton(onClick = {
                                        navController.navigate("actors")
                                        selectedItem = 3
                                    }) {
                                        Icon(
                                            Icons.Filled.Person,
                                            contentDescription = "Go to Actors",
                                            tint = if (selectedItem == 3) Color.White else Color.Gray
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            )
            {

                Box(modifier = Modifier.padding(it)) {
                    NavHost(
                        navController = navController,
                        startDestination = "Screen"
                    ) {
                        composable("Screen") {
                            Profil(windowSizeClass, navController)
                        }
                        composable("movies") {
                            Movies(navController, viewModel,windowSizeClass)
                        }
                        composable("movie/{id}") {
                            Movie(id=navBackStackEntry?.arguments?.getString("id"),viewModel)
                        }

                        composable("actors") {
                            Actors(navController, viewModel)
                        }

                        composable("series") {
                            Series(navController, viewModel, windowSizeClass)
                        }
                        composable("serie/{id}"){
                            Serie(id=navBackStackEntry?.arguments?.getString("id"),viewModel)
                        }




                    }
                }

            }
        }


    }
}











