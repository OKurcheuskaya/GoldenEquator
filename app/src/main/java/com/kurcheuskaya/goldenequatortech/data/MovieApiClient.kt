package com.kurcheuskaya.goldenequatortech.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiClient {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @retrofit2.http.Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieResponse

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(apiKey: String): MovieApiClient {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MovieApiClient::class.java)
        }
    }
}