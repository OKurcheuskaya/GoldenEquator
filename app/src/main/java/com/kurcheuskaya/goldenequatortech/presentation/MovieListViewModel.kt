package com.kurcheuskaya.goldenequatortech.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurcheuskaya.goldenequatortech.domain.Movie
import com.kurcheuskaya.goldenequatortech.domain.MovieUseCase
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val movieList = mutableListOf<Movie>()

                // Call the movieUseCase to get a list of movies
                // Replace the example implementation with your own logic.
                val movieIds = listOf(1, 2, 3) // Example movie IDs
                for (movieId in movieIds) {
                    val movie = movieUseCase.getMovieDetails(movieId)
                    movieList.add(movie)
                }

                _movies.value = movieList
            } catch (e: Exception) {
                _error.value = "Failed to fetch movies: ${e.message}"
            }
        }
    }
}
