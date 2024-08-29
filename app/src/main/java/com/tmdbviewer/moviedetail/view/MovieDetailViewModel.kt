package com.tmdbviewer.moviedetail.view

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdbviewer.common.navigation.NavigationConstants
import com.tmdbviewer.moviedetail.domain.GetMovieDetailsUseCase
import com.tmdbviewer.moviedetail.domain.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
  private val movieDetailUseCase: GetMovieDetailsUseCase,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val movieId =
    savedStateHandle.get<String>(NavigationConstants.MOVIE_DETAIL_ID_PARAM)?.toIntOrNull() ?: 0

  private val _movie = MutableStateFlow<MovieDetail?>(null)
  val movie: StateFlow<MovieDetail?> = _movie.asStateFlow()

  init {
    viewModelScope.launch {
      val movieDetails = movieDetailUseCase(movieId)
      _movie.value = movieDetails
    }
  }
}
