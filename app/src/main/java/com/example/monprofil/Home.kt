package com.example.monprofil

import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monprofil.ui.theme.MonProfilTheme




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Box(contentAlignment = Alignment.TopCenter) {
        Image(
            painterResource(id = R.drawable.monimage),
            contentDescription = "Mon image",
            modifier = Modifier
                .clip(CircleShape)
                .size(150.dp)
                .border(
                    BorderStroke(2.dp, Color.DarkGray),
                    CircleShape
                )
                .padding(2.dp),
            contentScale = ContentScale.Crop,

            )
    }
}


@Composable
fun Profil(windowClass: WindowSizeClass, navController: NavHostController) {
    when (windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(id = R.drawable.monimage),
                        contentDescription = "Mon image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(150.dp)
                            .border(
                                BorderStroke(2.dp, Color.DarkGray),
                                CircleShape
                            )
                            .padding(2.dp),
                        contentScale = ContentScale.Crop,

                        )

                    Text(
                        text = "Yousra Mahjoub",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(350.dp)
                    )
                }


                Text(
                    "Etudiante en 4 éme année cycle d'ingenieur en informatique de santé 2023/2025",
                    Modifier.width(350.dp),
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic

                )
                Column() {
                    Row() {
                        Icon(
                            Icons.Rounded.Call,
                            contentDescription = stringResource(id = R.string.call_content_desc),
                            Modifier.padding(3.dp)

                        )
                        Text(
                            text = "https://www.google.com/intl/fr/gmail",
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    Row() {
                        Icon(
                            Icons.Rounded.Email,
                            contentDescription = stringResource(id = R.string.attach_email_content_desc),
                            Modifier.padding(3.dp),

                            )
                        Text(
                            text = "https://www.google.com/intl/fr/gmail",
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic
                        )
                    }

                }

                Button(onClick = {
                    navController.navigate("movies")
                    //your onclick code here
                }) {
                    Text(text = "Démarrer")
                }


            }
        }

        else -> {

            Row(
                Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f, true),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painterResource(id = R.drawable.monimage),
                        contentDescription = "Mon image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(150.dp)
                            .border(
                                BorderStroke(2.dp, Color.DarkGray),
                                CircleShape
                            )
                            .padding(2.dp),
                        contentScale = ContentScale.Crop,

                        )

                    Text(
                        text = "Yousra Mahjoub",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(350.dp)
                    )
                    Text(
                        "Etudiante en 4 éme année cycle d'ingenieur en informatique de santé 2023/2025",
                        Modifier.width(350.dp),
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic

                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f, true),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Column() {
                        Row() {
                            Icon(
                                Icons.Rounded.Call,
                                contentDescription = stringResource(id = R.string.call_content_desc),
                                Modifier.padding(3.dp)
                            )
                            Text(
                                text = "https://www.google.com/intl/fr/gmail",
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic
                            )
                        }

                        Row() {
                            Icon(
                                Icons.Rounded.Email,
                                contentDescription = stringResource(id = R.string.attach_email_content_desc),
                                Modifier.padding(3.dp)

                            )
                            Text(
                                text = "https://www.google.com/intl/fr/gmail",
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic
                            )
                        }

                    }
                    Button(onClick = {
                        navController.navigate("movies")
                        //your onclick code here
                    }) {
                        Text(text = "Démarrer")
                    }

                }


            }


        }


    }



}