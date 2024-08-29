package com.tmdbviewer.movielist.data

import com.tmdbviewer.common.data.ApiConstants
import com.tmdbviewer.movielist.domain.Movie
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieDtoTest {

  @Test
  fun toDomainModel_withValidData() {
    val movieDto = MovieDto(
      id = 1,
      title = "Sample Movie",
      overview = "This is a sample movie.",
      posterPath = "/samplePoster.jpg",
      releaseDate = "2023-01-01"
    )

    val movie: Movie = movieDto.toDomainModel()

    assertEquals(1, movie.id)
    assertEquals("Sample Movie", movie.title)
    assertEquals("This is a sample movie.", movie.overview)
    assertEquals("${ApiConstants.BASE_IMAGE_URL}/samplePoster.jpg", movie.posterUrl)
    assertEquals("2023-01-01", movie.releaseDate)
  }

  @Test
  fun toDomainModel_withNullPosterPath() {
    val movieDto = MovieDto(
      id = 1,
      title = "Sample Movie",
      overview = "This is a sample movie.",
      posterPath = null,
      releaseDate = "2023-01-01"
    )

    val movie: Movie = movieDto.toDomainModel()

    assertEquals(1, movie.id)
    assertEquals("Sample Movie", movie.title)
    assertEquals("This is a sample movie.", movie.overview)
    assertEquals(null, movie.posterUrl)
    assertEquals("2023-01-01", movie.releaseDate)
  }
}