package com.tmdbviewer.movielist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdbviewer.R
import com.tmdbviewer.common.navigation.NavigationConstants
import com.tmdbviewer.common.view.ErrorMessage
import com.tmdbviewer.movielist.domain.Movie

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListScreen(navController: NavController) {
  val viewModel: MovieListViewModel = hiltViewModel()

  val movieItems: LazyPagingItems<Movie> = viewModel.movies.collectAsLazyPagingItems()
  val searchedMovieItems: LazyPagingItems<Movie> =
    viewModel.searchedMovies.collectAsLazyPagingItems()
  val query by viewModel.query.collectAsState()
  val context = LocalContext.current

  Column(modifier = Modifier.fillMaxSize()) {

    Text(
      modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
      text = context.getString(R.string.app_name),
      style = MaterialTheme.typography.h6
    )

    SearchBar(
      query = query,
      onQueryChanged = viewModel::onQueryChanged,
      onClearQuery = viewModel::clearQuery
    )

    val refreshState = rememberPullRefreshState(
      refreshing = movieItems.loadState.refresh is LoadState.Loading && movieItems.itemCount != 0,
      onRefresh = { movieItems.refresh() }
    )

    val isRefreshing by remember { derivedStateOf { movieItems.loadState.refresh is LoadState.Loading && movieItems.itemCount != 0 } }

    if (query.isEmpty()) {
      when {
        movieItems.loadState.refresh is LoadState.Error -> {
          val error = movieItems.loadState.refresh as LoadState.Error
          ErrorMessage(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            message = context.getString(R.string.something_went_wrong),
            onClickRetry = { movieItems.retry() }
          )
        }

        movieItems.itemCount == 0 && movieItems.loadState.refresh !is LoadState.Loading -> {
          Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = context.getString(R.string.no_movies_found)
          )
        }

        else ->
          Box(
            modifier = Modifier
              .fillMaxSize()
              .pullRefresh(refreshState)
          ) {
            MoveList(movieItems, navController)
            PullRefreshIndicator(
              refreshing = isRefreshing,
              state = refreshState,
              modifier = Modifier
                .align(Alignment.TopCenter)
                .zIndex(1f)
            )
          }
      }
    } else {
      MoveList(searchedMovieItems, navController)
    }
  }
}

@Composable
fun MoveList(items: LazyPagingItems<Movie>, navController: NavController) {
  val context = LocalContext.current
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(count = items.itemCount) { index ->
      items[index]?.let {
        MovieItem(movie = it, onClick = {
          navController.navigate("${NavigationConstants.MOVIE_DETAIL_SCREEN}/${it.id}")
        })
      }
    }

    items.apply {
      when {
        loadState.refresh is LoadState.Loading -> {
          item { ShimmerMoviePlaceholder() }
        }

        loadState.refresh is LoadState.Error -> {
          item {
            ErrorMessage(
              modifier = Modifier.fillParentMaxSize(),
              message = context.getString(R.string.something_went_wrong),
              onClickRetry = { retry() })
          }
        }

        loadState.append is LoadState.Loading -> {
          item { ShimmerMoviePlaceholder() }
        }

        loadState.append is LoadState.Error -> {
          item {
            ErrorMessage(
              modifier = Modifier.fillParentMaxSize(),
              message = context.getString(R.string.something_went_wrong),
              onClickRetry = { retry() })
          }
        }
      }
    }
  }
}