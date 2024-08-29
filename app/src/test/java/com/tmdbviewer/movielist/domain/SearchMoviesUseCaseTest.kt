package com.tmdbviewer.movielist.domain

import com.tmdbviewer.common.data.MovieRepository
import com.tmdbviewer.movielist.view.SearchParams
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SearchMoviesUseCaseTest {

  private val repository: MovieRepository = mock(MovieRepository::class.java)
  private val useCase = SearchMoviesUseCase(repository)

  @Test
  fun returnsMovies_whenRepositoryHasMovies() = runBlocking {
    val params = SearchParams(query = "Sample", page = 1)
    val movies = listOf(
      Movie(
        id = 1,
        title = "Movie 1",
        overview = "Overview 1",
        posterUrl = "/poster1.jpg",
        releaseDate = "2023-01-01"
      ),
      Movie(
        id = 2,
        title = "Movie 2",
        overview = "Overview 2",
        posterUrl = "/poster2.jpg",
        releaseDate = "2023-01-02"
      )
    )
    `when`(repository.searchMovies(params)).thenReturn(movies)

    val result = useCase.invoke(params)

    assertEquals(movies, result)
  }

  @Test
  fun returnsEmptyList_whenRepositoryHasNoMovies() = runBlocking {
    val params = SearchParams(query = "Sample", page = 1)
    `when`(repository.searchMovies(params)).thenReturn(emptyList())

    val result = useCase.invoke(params)

    assertEquals(emptyList<Movie>(), result)
  }

  @Test
  fun throwsException_whenRepositoryFails() = runBlocking {
    val params = SearchParams(query = "Sample", page = 1)
    `when`(repository.searchMovies(params)).thenThrow(RuntimeException("Repository error"))

    try {
      useCase.invoke(params)
      fail("Expected an exception to be thrown")
    } catch (e: RuntimeException) {
      assertEquals("Repository error", e.message)
    }
  }

  @Test
  fun returnsMovies_withDifferentQueries() = runBlocking {
    val params = SearchParams(query = "Different Query", page = 1)
    val movies = listOf(
      Movie(
        id = 1,
        title = "Movie 1",
        overview = "Overview 1",
        posterUrl = "/poster1.jpg",
        releaseDate = "2023-01-01"
      )
    )
    `when`(repository.searchMovies(params)).thenReturn(movies)

    val result = useCase.invoke(params)

    assertEquals(movies, result)
  }

  @Test
  fun returnsMovies_withDifferentPages() = runBlocking {
    val params = SearchParams(query = "Sample", page = 2)
    val movies = listOf(
      Movie(
        id = 1,
        title = "Movie 1",
        overview = "Overview 1",
        posterUrl = "/poster1.jpg",
        releaseDate = "2023-01-01"
      ),
      Movie(
        id = 2,
        title = "Movie 2",
        overview = "Overview 2",
        posterUrl = "/poster2.jpg",
        releaseDate = "2023-01-02"
      )
    )
    `when`(repository.searchMovies(params)).thenReturn(movies)

    val result = useCase.invoke(params)

    assertEquals(movies, result)
  }
}