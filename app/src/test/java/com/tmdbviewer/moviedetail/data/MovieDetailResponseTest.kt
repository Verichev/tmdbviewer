package com.tmdbviewer.moviedetail.data

import com.tmdbviewer.common.data.ApiConstants
import com.tmdbviewer.moviedetail.domain.MovieDetail
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MovieDetailResponseTest {

  @Test
  fun toDomainModel() {
    val movieDetailResponse = MovieDetailResponse(
      id = 1,
      title = "Sample Movie",
      overview = "This is a sample movie.",
      posterPath = "/samplePoster.jpg",
      backdropPath = "/sampleBackdrop.jpg",
      releaseDate = "2023-01-01",
      voteAverage = 8.5,
      runtime = 120,
      genres = listOf(Genre(id = 1, name = "Action")),
      productionCompanies = listOf(
        ProductionCompany(
          id = 1,
          name = "Sample Production",
          logoPath = "/sampleLogo.jpg",
          originCountry = "US"
        )
      )
    )

    val movieDetail: MovieDetail = movieDetailResponse.toDomainModel()

    assertEquals(1, movieDetail.id)
    assertEquals("Sample Movie", movieDetail.title)
    assertEquals("This is a sample movie.", movieDetail.overview)
    assertEquals("${ApiConstants.BASE_IMAGE_URL}/samplePoster.jpg", movieDetail.posterUrl)
    assertEquals("${ApiConstants.BASE_IMAGE_URL}/sampleBackdrop.jpg", movieDetail.backdropUrl)
    assertEquals("2023-01-01", movieDetail.releaseDate)
    assertEquals(8.5, movieDetail.rating)
    assertEquals(120, movieDetail.runtime)
    assertEquals(listOf("Action"), movieDetail.genres)
    assertEquals(listOf("Sample Production"), movieDetail.productionCompanies)
  }
}