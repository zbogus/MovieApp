package com.zahitbogus.cleanmovieapp.presentation.movies.views

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.appcompat.widget.WithHint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zahitbogus.cleanmovieapp.presentation.Screen
import com.zahitbogus.cleanmovieapp.presentation.movies.MoviesEvent
import com.zahitbogus.cleanmovieapp.presentation.movies.MoviesViewModel


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()

){
    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column {
            MovieSearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                }
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies){movie ->
                    MovieListRow(movie = movie, onItemClick =   {
                        navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}")
                        
                    })
                }

            }
        }
    }
}

@Composable
fun MovieSearchBar(
    modifier: Modifier,
    hint: String = "",
    onSearch: (String) ->Unit = {}
){
    var text by remember{
        mutableStateOf("")
    }
    var isHintDisplayed by remember{
        mutableStateOf(hint != "")
    }
    Box(modifier = modifier){
        androidx.compose.material.TextField(value =text , onValueChange ={
            text = it
        },
            keyboardActions = KeyboardActions(onDone = {
                onSearch(text)
            }),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )
            if (isHintDisplayed){
                Text(text = hint, color = Color.LightGray, modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
            }
        
    }
}

