package com.tmdbviewer.movielist.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdbviewer.movielist.domain.GetLatestMoviesUseCase
import com.tmdbviewer.movielist.domain.Movie

class MovieListPagingSource(
  private val moviesUseCase: GetLatestMoviesUseCase
) : PagingSource<Int, Movie>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
    return try {
      val currentPage = params.key ?: 1
      val response = moviesUseCase(currentPage)
      val nextPage = if (response.isEmpty()) null else currentPage + 1

      LoadResult.Page(
        data = response,
        prevKey = if (currentPage == 1) null else currentPage - 1,
        nextKey = nextPage
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
    return null
  }
}