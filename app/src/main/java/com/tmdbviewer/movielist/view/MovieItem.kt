package com.tmdbviewer.movielist.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.tmdbviewer.R
import com.tmdbviewer.movielist.domain.Movie

@Composable
fun MovieItem(
  movie: Movie,
  onClick: () -> Unit,
  imageWidth: Dp = 80.dp // Control only the width
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .clickable { onClick() }
  ) {
    Image(
      painter = rememberAsyncImagePainter(
        model = movie.posterUrl,
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder)
      ),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .width(imageWidth)
        .aspectRatio(2 / 3f) // If aspect ratio is not fixed, use logic, dynamically to resolve it
    )
    Spacer(modifier = Modifier.width(16.dp))
    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
      Text(
        modifier = Modifier.fillMaxWidth(),
        text = movie.title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.h6
      )
      if (movie.overview.isNotEmpty()) {
        Text(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
          text = movie.overview,
          maxLines = 3,
          overflow = TextOverflow.Ellipsis,
          style = MaterialTheme.typography.body2
        )
      }
    }
  }
}