package com.kurcheuskaya.goldenequatortech.domain

import com.kurcheuskaya.goldenequatortech.data.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMovieDetails(movieId: Int): Movie {
        val movieResponse = movieRepository.getMovieDetails(movieId)
        return Movie(
            movieResponse.id,
            movieResponse.title,
            movieResponse.overview,
            movieResponse.releaseDate,
            movieResponse.posterPath
        )
    }
}
