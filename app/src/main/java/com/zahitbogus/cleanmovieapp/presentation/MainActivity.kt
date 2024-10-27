package com.zahitbogus.cleanmovieapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zahitbogus.cleanmovieapp.presentation.movie_detail.views.MovieDetailScreen
import com.zahitbogus.cleanmovieapp.presentation.movies.views.MovieScreen
import com.zahitbogus.cleanmovieapp.presentation.ui.theme.CleanMovieAppTheme
import com.zahitbogus.cleanmovieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanMovieAppTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background){
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MovieScreen.route){
                        composable(route = Screen.MovieScreen.route){
                            MovieScreen(navController = navController)
                        }

                        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}"){
                            MovieDetailScreen()
                        }


                    }          }
            }
        }
    }
}

