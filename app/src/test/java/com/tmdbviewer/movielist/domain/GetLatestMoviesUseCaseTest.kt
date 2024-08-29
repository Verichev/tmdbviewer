package com.tmdbviewer.movielist.domain

import com.tmdbviewer.common.data.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetLatestMoviesUseCaseTest {

  private val repository: MovieRepository = mock(MovieRepository::class.java)
  private val useCase = GetLatestMoviesUseCase(repository)

  @Test
  fun returnsMovies_whenRepositoryHasMovies() = runBlocking {
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
    `when`(repository.getLatestMovies(2)).thenReturn(movies)

    val result = useCase.invoke(2)

    assertEquals(movies, result)
  }

  @Test
  fun returnsEmptyList_whenRepositoryHasNoMovies() = runBlocking {
    `when`(repository.getLatestMovies(0)).thenReturn(emptyList())

    val result = useCase.invoke(0)

    assertEquals(emptyList<Movie>(), result)
  }

  @Test
  fun throwsException_whenRepositoryFails() = runBlocking {
    `when`(repository.getLatestMovies(1)).thenThrow(RuntimeException("Repository error"))

    try {
      useCase.invoke(1)
      fail("Expected an exception to be thrown")
    } catch (e: RuntimeException) {
      assertEquals("Repository error", e.message)
    }
  }

  @Test
  fun returnsMovies_withDifferentCounts() = runBlocking {
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
      ),
      Movie(
        id = 3,
        title = "Movie 3",
        overview = "Overview 3",
        posterUrl = "/poster3.jpg",
        releaseDate = "2023-01-03"
      )
    )
    `when`(repository.getLatestMovies(3)).thenReturn(movies)

    val result = useCase.invoke(3)

    assertEquals(movies, result)
  }
}