package com.tmdbviewer.movielist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdbviewer.movielist.domain.GetLatestMoviesUseCase
import com.tmdbviewer.movielist.domain.Movie
import com.tmdbviewer.movielist.domain.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
  private val getLatestMoviesUseCase: GetLatestMoviesUseCase,
  private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

  private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
  val movies: StateFlow<PagingData<Movie>> = _movies.asStateFlow()

  private val _searchedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
  val searchedMovies: StateFlow<PagingData<Movie>> = _searchedMovies.asStateFlow()

  private val _query = MutableStateFlow("")
  val query: StateFlow<String> = _query.asStateFlow()

  private val pagingMovieListDataFlow = Pager(PagingConfig(pageSize = 20)) {
    MovieListPagingSource(getLatestMoviesUseCase)
  }.flow.cachedIn(viewModelScope)


  init {
    loadMovies()
  }

  private fun loadMovies() {
    viewModelScope.launch {
      pagingMovieListDataFlow.collect { pagingData ->
        _movies.value = pagingData
      }
    }
  }

  fun onQueryChanged(newQuery: String) {
    _query.value = newQuery
    if (newQuery.isNotEmpty()) {
      val pagingMovieSearchDataFlow = Pager(PagingConfig(pageSize = 20)) {
        MovieSearchPagingSource(searchMoviesUseCase, newQuery)
      }.flow.cachedIn(viewModelScope)

      viewModelScope.launch {
        pagingMovieSearchDataFlow.collect { pagingData ->
          _searchedMovies.value = pagingData
        }
      }
    } else {
      _searchedMovies.value = PagingData.empty()
    }
  }

  fun clearQuery() {
    _query.value = ""
    _searchedMovies.value = PagingData.empty()
  }
}