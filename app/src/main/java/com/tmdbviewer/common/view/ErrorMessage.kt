package com.tmdbviewer.common.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorMessage(
  modifier: Modifier = Modifier,
  message: String,
  onClickRetry: () -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = message,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.h6,
      modifier = Modifier.padding(bottom = 8.dp)
    )
    Button(onClick = onClickRetry) {
      Text(text = "Retry")
    }
  }
}