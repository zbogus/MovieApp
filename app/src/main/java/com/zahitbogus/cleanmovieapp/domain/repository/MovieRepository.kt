package com.zahitbogus.cleanmovieapp.domain.repository

import com.zahitbogus.cleanmovieapp.data.remote.dto.MovieDetailDto
import com.zahitbogus.cleanmovieapp.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(search: String): MoviesDto

    suspend fun getMovieDetail(imdbId: String): MovieDetailDto

}