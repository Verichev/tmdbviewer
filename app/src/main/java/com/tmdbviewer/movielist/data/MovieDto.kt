package com.tmdbviewer.movielist.data

import com.google.gson.annotations.SerializedName
import com.tmdbviewer.common.data.ApiConstants
import com.tmdbviewer.movielist.domain.Movie

data class MovieDto(
  @SerializedName("id") val id: Int,
  @SerializedName("title") val title: String,
  @SerializedName("overview") val overview: String,
  @SerializedName("poster_path") val posterPath: String?,
  @SerializedName("release_date") val releaseDate: String
)

fun MovieDto.toDomainModel(): Movie {
  return Movie(
    id = id,
    title = title,
    overview = overview,
    posterUrl = posterPath?.let { "${ApiConstants.BASE_IMAGE_URL}$it" },
    releaseDate = releaseDate
  )
}