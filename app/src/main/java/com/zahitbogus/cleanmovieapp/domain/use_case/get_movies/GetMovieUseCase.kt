package com.zahitbogus.cleanmovieapp.domain.use_case.get_movies

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.zahitbogus.cleanmovieapp.data.remote.dto.toMovieList
import com.zahitbogus.cleanmovieapp.domain.model.Movie
import com.zahitbogus.cleanmovieapp.domain.repository.MovieRepository
import com.zahitbogus.cleanmovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {
        try{
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)
            if(movieList.Response == "True"){
                emit(Resource.Success(movieList.toMovieList()))
            }else {
                emit(Resource.Error("No movie found"))
            }
        }catch (e : IOError){
            emit(Resource.Error("No internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }
}