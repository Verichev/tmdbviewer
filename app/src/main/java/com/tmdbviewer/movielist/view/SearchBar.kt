package com.tmdbviewer.movielist.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.tmdbviewer.R

@Composable
fun SearchBar(query: String, onQueryChanged: (String) -> Unit, onClearQuery: () -> Unit) {
  val keyboardController = LocalSoftwareKeyboardController.current
  val context = LocalContext.current
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    TextField(
      value = query,
      onValueChange = onQueryChanged,
      placeholder = { Text(context.getString(R.string.search)) },
      modifier = Modifier.fillMaxWidth()
    )
    if (query.isNotEmpty()) {
      IconButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = {
        keyboardController?.hide()
        onClearQuery()
      }) {
        Icon(Icons.Default.Close, contentDescription = "Clear")
      }
    }
  }
}