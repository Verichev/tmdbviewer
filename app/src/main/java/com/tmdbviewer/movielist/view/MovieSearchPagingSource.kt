package com.tmdbviewer.movielist.view

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdbviewer.movielist.domain.Movie
import com.tmdbviewer.movielist.domain.SearchMoviesUseCase

class MovieSearchPagingSource(
  private val searchMoviesUseCase: SearchMoviesUseCase,
  private val query: String
) : PagingSource<Int, Movie>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
    val currentPage = params.key ?: 1
    return try {
      val response = searchMoviesUseCase(SearchParams(query, currentPage))
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

data class SearchParams(val query: String, val page: Int) {
  companion object {
    fun create(query: String, page: Int = 1): SearchParams {
      return SearchParams(query, page)
    }
  }
}
