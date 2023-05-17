package com.kurcheuskaya.goldenequatortech.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurcheuskaya.goldenequatortech.domain.Movie
import com.kurcheuskaya.goldenequatortech.domain.MovieUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movie = movieUseCase.getMovieDetails(movieId)
                _movie.value = movie
            } catch (e: Exception) {
                _error.value = "Failed to fetch movie details: ${e.message}"
            }
        }
    }
}
