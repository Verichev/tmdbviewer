package com.tmdbviewer.moviedetail.domain

import com.tmdbviewer.common.data.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetMovieDetailsUseCaseTest {

  private val repository: MovieRepository = mock(MovieRepository::class.java)
  private val useCase = GetMovieDetailsUseCase(repository)

  @Test
  fun returnsMovieDetails_withValidData() = runBlocking {
    val movieDetail = MovieDetail(
      id = 1,
      title = "Movie 1",
      overview = "Overview 1",
      posterUrl = "/poster1.jpg",
      backdropUrl = "/backdrop1.jpg",
      releaseDate = "2023-01-01",
      rating = 8.5,
      runtime = 120,
      genres = listOf("Action", "Adventure"),
      productionCompanies = listOf("Company 1", "Company 2")
    )
    `when`(repository.getMovieDetails(1)).thenReturn(movieDetail)

    val result = useCase.invoke(1)

    assertEquals(movieDetail, result)
  }

  @Test
  fun returnsMovieDetails_withNullPosterUrl() = runBlocking {
    val movieDetail = MovieDetail(
      id = 1,
      title = "Movie 1",
      overview = "Overview 1",
      posterUrl = null,
      backdropUrl = "/backdrop1.jpg",
      releaseDate = "2023-01-01",
      rating = 8.5,
      runtime = 120,
      genres = listOf("Action", "Adventure"),
      productionCompanies = listOf("Company 1", "Company 2")
    )
    `when`(repository.getMovieDetails(1)).thenReturn(movieDetail)

    val result = useCase.invoke(1)

    assertEquals(movieDetail, result)
  }

  @Test
  fun returnsMovieDetails_withNullBackdropUrl() = runBlocking {
    val movieDetail = MovieDetail(
      id = 1,
      title = "Movie 1",
      overview = "Overview 1",
      posterUrl = "/poster1.jpg",
      backdropUrl = null,
      releaseDate = "2023-01-01",
      rating = 8.5,
      runtime = 120,
      genres = listOf("Action", "Adventure"),
      productionCompanies = listOf("Company 1", "Company 2")
    )
    `when`(repository.getMovieDetails(1)).thenReturn(movieDetail)

    val result = useCase.invoke(1)

    assertEquals(movieDetail, result)
  }

  @Test
  fun returnsMovieDetails_withNullRuntime() = runBlocking {
    val movieDetail = MovieDetail(
      id = 1,
      title = "Movie 1",
      overview = "Overview 1",
      posterUrl = "/poster1.jpg",
      backdropUrl = "/backdrop1.jpg",
      releaseDate = "2023-01-01",
      rating = 8.5,
      runtime = null,
      genres = listOf("Action", "Adventure"),
      productionCompanies = listOf("Company 1", "Company 2")
    )
    `when`(repository.getMovieDetails(1)).thenReturn(movieDetail)

    val result = useCase.invoke(1)

    assertEquals(movieDetail, result)
  }

  @Test
  fun returnsMovieDetails_withEmptyGenres() = runBlocking {
    val movieDetail = MovieDetail(
      id = 1,
      title = "Movie 1",
      overview = "Overview 1",
      posterUrl = "/poster1.jpg",
      backdropUrl = "/backdrop1.jpg",
      releaseDate = "2023-01-01",
      rating = 8.5,
      runtime = 120,
      genres = emptyList(),
      productionCompanies = listOf("Company 1", "Company 2")
    )
    `when`(repository.getMovieDetails(1)).thenReturn(movieDetail)

    val result = useCase.invoke(1)

    assertEquals(movieDetail, result)
  }

  @Test
  fun returnsMovieDetails_withEmptyProductionCompanies() = runBlocking {
    val movieDetail = MovieDetail(
      id = 1,
      title = "Movie 1",
      overview = "Overview 1",
      posterUrl = "/poster1.jpg",
      backdropUrl = "/backdrop1.jpg",
      releaseDate = "2023-01-01",
      rating = 8.5,
      runtime = 120,
      genres = listOf("Action", "Adventure"),
      productionCompanies = emptyList()
    )
    `when`(repository.getMovieDetails(1)).thenReturn(movieDetail)

    val result = useCase.invoke(1)

    assertEquals(movieDetail, result)
  }
}