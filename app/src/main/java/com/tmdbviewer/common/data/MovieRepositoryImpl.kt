package com.tmdbviewer.common.data

import com.tmdbviewer.common.providers.DispatcherProvider
import com.tmdbviewer.moviedetail.data.toDomainModel
import com.tmdbviewer.moviedetail.domain.MovieDetail
import com.tmdbviewer.movielist.data.toDomainModel
import com.tmdbviewer.movielist.domain.Movie
import com.tmdbviewer.movielist.view.SearchParams
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MovieRepositoryImpl(
  private val movieApi: MovieApi,
  private val dispatcherProvider: DispatcherProvider
) : MovieRepository {

  private val currentDate: String
    get() {
      val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
      return dateFormat.format(Date())
    }

  override suspend fun getLatestMovies(page: Int): List<Movie> {
    return withContext(dispatcherProvider.io) {
      movieApi.getMovies(page, currentDate).results.map { it.toDomainModel() }
    }
  }

  override suspend fun searchMovies(params: SearchParams): List<Movie> {
    return withContext(dispatcherProvider.io) {
      movieApi.searchMovies(params.query, params.page).results.map { it.toDomainModel() }
    }
  }

  override suspend fun getMovieDetails(movieId: Int): MovieDetail {
    return withContext(dispatcherProvider.io) {
      movieApi.getMovieDetails(movieId).toDomainModel()
    }
  }
}