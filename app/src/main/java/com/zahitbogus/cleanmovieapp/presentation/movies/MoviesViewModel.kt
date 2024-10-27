package com.zahitbogus.cleanmovieapp.presentation.movies

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zahitbogus.cleanmovieapp.domain.use_case.get_movies.GetMovieUseCase
import com.zahitbogus.cleanmovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MoviesViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) :ViewModel() {
    private val _state = mutableStateOf<MoviesState>(MoviesState())
    val state : State<MoviesState> = _state

    private var job : Job? = null
    init {
        getMovies(_state.value.search)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getMovies(search: String){
        job?.cancel()
        job =getMovieUseCase.executeGetMovies(search = search).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value = MoviesState(error = it.message ?: "Error!")
                }
                is Resource.Loading ->{
                    _state.value = MoviesState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search ->{
                getMovies(event.searchString)
            }

        }
    }

}