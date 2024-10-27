package com.zahitbogus.cleanmovieapp.domain.use_case.get_movie_detail

import android.adservices.adid.AdId
import android.annotation.SuppressLint
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.zahitbogus.cleanmovieapp.data.remote.dto.toMovieDetail
import com.zahitbogus.cleanmovieapp.data.remote.dto.toMovieList
import com.zahitbogus.cleanmovieapp.domain.model.Movie
import com.zahitbogus.cleanmovieapp.domain.model.MovieDetail
import com.zahitbogus.cleanmovieapp.domain.repository.MovieRepository
import com.zahitbogus.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    @SuppressLint("SuspiciousIndentation")
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun executeGetMoviesDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {
        try{
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId)

                emit(Resource.Success(movieDetail.toMovieDetail()))

        }catch (e : IOError){
            emit(Resource.Error("No internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }
}