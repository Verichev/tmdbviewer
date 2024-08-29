package com.tmdbviewer.common.data

import com.tmdbviewer.movielist.data.MovieResponse
import com.tmdbviewer.movielist.view.SearchParams
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MovieRepositoryImplTest {

  private lateinit var movieApi: MovieApi
  private lateinit var movieRepository: MovieRepositoryImpl

  @BeforeEach
  fun setUp() {
    movieApi = mock(MovieApi::class.java)
    movieRepository = MovieRepositoryImpl(movieApi)
  }

  @Test
  fun getLatestMovies_shouldReturnEmptyList_whenNoMoviesAvailable() = runTest {
    val page = 1
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = dateFormat.format(Date())
    val movieResponse = MovieResponse(results = emptyList())

    `when`(movieApi.getMovies(page, currentDate)).thenReturn(movieResponse)

    val movies = movieRepository.getLatestMovies(page)

    assertEquals(0, movies.size)
  }

  @Test
  fun searchMovies_shouldReturnEmptyList_whenNoMoviesMatchQuery() = runTest {
    val params = SearchParams(query = "nonexistent", page = 1)
    val movieResponse = MovieResponse(results = emptyList())

    `when`(movieApi.searchMovies(params.query, params.page)).thenReturn(movieResponse)

    val movies = movieRepository.searchMovies(params)

    assertEquals(0, movies.size)
  }

  @Test
  fun getMovieDetails_shouldThrowException_whenMovieNotFound() = runTest {
    val movieId = 999
    `when`(movieApi.getMovieDetails(movieId)).thenThrow(RuntimeException("Movie not found"))

    try {
      movieRepository.getMovieDetails(movieId)
      fail("Expected an exception to be thrown")
    } catch (e: RuntimeException) {
      assertEquals("Movie not found", e.message)
    }
  }
}

