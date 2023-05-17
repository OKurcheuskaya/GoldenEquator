package com.kurcheuskaya.goldenequatortech.data

import com.kurcheuskaya.goldenequatortech.Const.Companion.API_KEY

interface MovieDataSource {
    suspend fun getMovieDetails(movieId: Int): MovieResponse
}

class MovieDataSourceImpl(private val movieApiClient: MovieApiClient) : MovieDataSource {
    override suspend fun getMovieDetails(movieId: Int): MovieResponse {
        return movieApiClient.getMovieDetails(movieId, API_KEY)
    }
}
