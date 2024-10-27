package com.zahitbogus.cleanmovieapp.presentation.movies

import com.zahitbogus.cleanmovieapp.domain.model.Movie

data class MoviesState (
    val isLoading: Boolean =false,
    val movies : List<Movie> = emptyList(),
    val error :String = "",
    val search : String = "batman"
)