package com.tmdbviewer.movielist.domain

import com.tmdbviewer.common.data.MovieRepository
import com.tmdbviewer.common.domain.UseCase

class GetLatestMoviesUseCase(
  private val repository: MovieRepository,
) : UseCase<Int, List<Movie>> {
  override suspend fun invoke(params: Int): List<Movie> {
    return repository.getLatestMovies(params)
  }
}