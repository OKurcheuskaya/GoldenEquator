package com.kurcheuskaya.goldenequatortech

import com.kurcheuskaya.goldenequatortech.data.MovieRepository
import com.kurcheuskaya.goldenequatortech.data.MovieResponse
import com.kurcheuskaya.goldenequatortech.domain.Movie
import com.kurcheuskaya.goldenequatortech.domain.MovieUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MovieUseCaseTest {

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var movieUseCase: MovieUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieUseCase = MovieUseCase(movieRepository)
    }

    @Test
    fun `test getMovieDetails() with valid movieId`() = runBlocking {
        // Arrange
        val movieId = 1
        val movieResponse = MovieResponse(
            id = movieId,
            title = "Test Movie",
            overview = "This is a test movie",
            releaseDate = "2023-05-01",
            posterPath = "test_poster.jpg"
        )
        val expectedMovie = Movie(
            id = movieId,
            title = "Test Movie",
            overview = "This is a test movie",
            releaseDate = "2023-05-01",
            posterPath = "test_poster.jpg"
        )

        `when`(movieRepository.getMovieDetails(movieId)).thenReturn(movieResponse)

        // Act
        val result = movieUseCase.getMovieDetails(movieId)

        // Assert
        assertEquals(expectedMovie, result)
    }
}
