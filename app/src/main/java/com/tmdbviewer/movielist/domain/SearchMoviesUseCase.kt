package com.tmdbviewer.movielist.domain

import com.tmdbviewer.common.data.MovieRepository
import com.tmdbviewer.common.domain.UseCase
import com.tmdbviewer.movielist.view.SearchParams

class SearchMoviesUseCase(private val repository: MovieRepository) :
  UseCase<SearchParams, List<Movie>> {
  override suspend operator fun invoke(params: SearchParams): List<Movie> {
    return repository.searchMovies(params)
  }
}