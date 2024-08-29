package com.tmdbviewer.moviedetail.data

import com.google.gson.annotations.SerializedName
import com.tmdbviewer.common.data.ApiConstants
import com.tmdbviewer.moviedetail.domain.MovieDetail

data class MovieDetailResponse(
  @SerializedName("id") val id: Int,
  @SerializedName("title") val title: String,
  @SerializedName("overview") val overview: String,
  @SerializedName("poster_path") val posterPath: String?,
  @SerializedName("backdrop_path") val backdropPath: String?,
  @SerializedName("release_date") val releaseDate: String,
  @SerializedName("vote_average") val voteAverage: Double,
  @SerializedName("runtime") val runtime: Int?,
  @SerializedName("genres") val genres: List<Genre>,
  @SerializedName("production_companies") val productionCompanies: List<ProductionCompany>
)

data class Genre(
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String
)

data class ProductionCompany(
  @SerializedName("id") val id: Int,
  @SerializedName("name") val name: String,
  @SerializedName("logo_path") val logoPath: String?,
  @SerializedName("origin_country") val originCountry: String
)

fun MovieDetailResponse.toDomainModel(): MovieDetail {
  return MovieDetail(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterPath?.let { "${ApiConstants.BASE_IMAGE_URL}$it" },
    backdropUrl = backdropPath?.let { "${ApiConstants.BASE_IMAGE_URL}$it" },
    releaseDate = releaseDate,
    rating = voteAverage,
    runtime = runtime,
    genres = genres.map { it.name },
    productionCompanies = productionCompanies.map { it.name }
  )
}

