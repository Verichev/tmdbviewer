package com.tmdbviewer.movielist.domain

data class Movie(
  val id: Int,
  val title: String,
  val overview: String,
  val posterUrl: String?,
  val releaseDate: String
)