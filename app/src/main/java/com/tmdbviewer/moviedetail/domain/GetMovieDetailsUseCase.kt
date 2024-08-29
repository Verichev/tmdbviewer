package com.tmdbviewer.moviedetail.domain

import com.tmdbviewer.common.data.MovieRepository
import com.tmdbviewer.common.domain.UseCase

class GetMovieDetailsUseCase(
  private val repository: MovieRepository,
) : UseCase<Int, MovieDetail> {
  override suspend fun invoke(params: Int): MovieDetail {
    return repository.getMovieDetails(params)
  }
}