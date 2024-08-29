package com.tmdbviewer.common.data

import com.tmdbviewer.movielist.domain.Movie
import com.tmdbviewer.moviedetail.domain.MovieDetail
import com.tmdbviewer.movielist.view.SearchParams

interface MovieRepository {
  suspend fun getLatestMovies(page: Int): List<Movie>
  suspend fun searchMovies(params: SearchParams): List<Movie>
  suspend fun getMovieDetails(movieId: Int): MovieDetail
}