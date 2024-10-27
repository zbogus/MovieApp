package com.zahitbogus.cleanmovieapp.presentation.movie_detail

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahitbogus.cleanmovieapp.domain.use_case.get_movie_detail.GetMovieDetailUseCase
import com.zahitbogus.cleanmovieapp.util.Constants.IMDB_ID
import com.zahitbogus.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val stateHandle : SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState())
    val state : State<MovieDetailState> = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let{
            getMovieDetail(it)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getMovieDetail(imdbId: String){
        getMovieDetailUseCase.executeGetMoviesDetails(imdbId = imdbId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = MovieDetailState(movie = it.data)
                }
                is Resource.Error ->{
                    _state.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading ->{
                    _state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}