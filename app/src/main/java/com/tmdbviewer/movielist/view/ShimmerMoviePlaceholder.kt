package com.tmdbviewer.movielist.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerMoviePlaceholder() {
  Row(
    modifier = Modifier
      .shimmer()
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    Box(
      modifier = Modifier
        .size(80.dp)
        .background(Color.LightGray),
    )
    Column(
      verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(24.dp)
          .background(Color.LightGray),
      )
      Box(
        modifier = Modifier
          .fillMaxWidth(0.6f)
          .height(20.dp)
          .background(Color.LightGray),
      )
    }
  }
}
