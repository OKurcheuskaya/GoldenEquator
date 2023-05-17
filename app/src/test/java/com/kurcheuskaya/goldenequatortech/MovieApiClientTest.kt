package com.kurcheuskaya.goldenequatortech

import com.google.gson.GsonBuilder
import com.kurcheuskaya.goldenequatortech.data.MovieApiClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiClientTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var movieApiClient: MovieApiClient

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        movieApiClient = retrofit.create(MovieApiClient::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    suspend fun `test getMovieDetails() with valid movieId`() {
        // Arrange
        val movieId = 1
        val apiKey = "test_api_key"

        val responseBody = """
            {
                "id": 1,
                "title": "Test Movie",
                "overview": "This is a test movie",
                "release_date": "2023-05-01",
                "poster_path": "test_poster.jpg"
            }
        """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        // Act
        val response = movieApiClient.getMovieDetails(movieId, apiKey)

        // Assert
        assertEquals(movieId, response.id)
        assertEquals("Test Movie", response.title)
        assertEquals("This is a test movie", response.overview)
        assertEquals("2023-05-01", response.releaseDate)
        assertEquals("test_poster.jpg", response.posterPath)

        // Verify the request
        val request = mockWebServer.takeRequest()
        assertEquals("/movie/$movieId?api_key=$apiKey", request.path)
    }
}
