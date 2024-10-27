package com.zahitbogus.cleanmovieapp.data.remote

import com.zahitbogus.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.zahitbogus.cleanmovieapp.data.remote.dto.MoviesDto
import com.zahitbogus.cleanmovieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI  {


    // https://www.omdbapi.com/?apikey=ff601a89&s=batman
    // https://www.omdbapi.com/?apikey=ff601a89&i=tt3896198

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString : String,
        @Query("apikey") apiKey : String = API_KEY
    ) : MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey : String = API_KEY
    ) :MovieDetailDto

}