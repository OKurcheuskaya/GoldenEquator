package com.kurcheuskaya.goldenequatortech.data

interface MovieRepository {
    suspend fun getMovieDetails(movieId: Int): MovieResponse
}

class MovieRepositoryImpl(private val movieDataSource: MovieDataSource) : MovieRepository {
    override suspend fun getMovieDetails(movieId: Int): MovieResponse {
        return movieDataSource.getMovieDetails(movieId)
    }
}
