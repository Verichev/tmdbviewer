package com.tmdbviewer.moviedetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.tmdbviewer.moviedetail.domain.MovieDetail

@Composable
fun MovieDetailScreen() {
  val viewModel: MovieDetailViewModel = hiltViewModel()
  val movie: MovieDetail? = viewModel.movie.collectAsState().value

  movie?.let { movieDetail ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .verticalScroll(rememberScrollState())
    ) {
      movieDetail.posterUrl?.let { // could be used backdropUrl
        Image(
          painter = rememberAsyncImagePainter(it),
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2 / 3f) //aspect ratio of the image. If not fixed, should be calculated dynamically
        )
      }

      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
      ) {
        Text(
          text = movieDetail.title,
          style = MaterialTheme.typography.h4,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Released: ${movieDetail.releaseDate}",
          style = MaterialTheme.typography.subtitle2,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Rating: ${movieDetail.rating}/10",
          style = MaterialTheme.typography.subtitle2,
          modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
          text = movieDetail.overview,
          style = MaterialTheme.typography.body1,
          modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
          text = "Runtime: ${movieDetail.runtime ?: "N/A"} minutes",
          style = MaterialTheme.typography.subtitle2,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Genres: ${movieDetail.genres.joinToString(", ")}",
          style = MaterialTheme.typography.subtitle2,
          modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
          text = "Production Companies: ${movieDetail.productionCompanies.joinToString(", ")}",
          style = MaterialTheme.typography.subtitle2,
          modifier = Modifier.padding(bottom = 8.dp)
        )
      }
    }
  } ?: run {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
      CircularProgressIndicator()
    }
  }
}
