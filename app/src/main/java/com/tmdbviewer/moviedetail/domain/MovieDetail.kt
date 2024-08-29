package com.tmdbviewer.moviedetail.domain

data class MovieDetail(
  val id: Int,
  val title: String,
  val overview: String,
  val posterUrl: String?,
  val backdropUrl: String?,
  val releaseDate: String,
  val rating: Double,
  val runtime: Int?,
  val genres: List<String>,
  val productionCompanies: List<String>
)
