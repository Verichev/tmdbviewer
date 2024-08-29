package com.tmdbviewer.common.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tmdbviewer.common.navigation.NavigationConstants
import com.tmdbviewer.moviedetail.view.MovieDetailScreen
import com.tmdbviewer.movielist.view.MovieListScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
  NavHost(navController = navController, startDestination = NavigationConstants.MOVIE_LIST_SCREEN) {
    composable(NavigationConstants.MOVIE_LIST_SCREEN) {
      MovieListScreen(navController = navController)
    }
    composable("${NavigationConstants.MOVIE_DETAIL_SCREEN}/{${NavigationConstants.MOVIE_DETAIL_ID_PARAM}}") { _ ->
      MovieDetailScreen()
    }
  }
}