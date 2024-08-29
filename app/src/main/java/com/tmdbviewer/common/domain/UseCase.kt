package com.tmdbviewer.common.domain

interface UseCase<in P, R> {
  suspend operator fun invoke(params: P): R
}