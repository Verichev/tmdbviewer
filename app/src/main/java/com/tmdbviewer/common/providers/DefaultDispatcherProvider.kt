package com.tmdbviewer.common.providers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider : DispatcherProvider {
  override val main: CoroutineDispatcher = Dispatchers.Main
  override val io: CoroutineDispatcher = Dispatchers.IO
  override val default: CoroutineDispatcher = Dispatchers.Default
  override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
}