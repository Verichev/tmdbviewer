package com.tmdbviewer.common.data

object ApiConstants {
  const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
  const val BASE_URL = "https://api.themoviedb.org/3/"

  //this production key should be protected, either by injection from CI (and using other key for local development) or by fetching it from backend.
  // Actually better implementation would be using this api and key only from backend, and use additional authorisation to access this methods
  const val TMDB_API_KEY =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MWZlZjU3ZmE4ZmQyMGY3N2VjNzE2NGE5MTc0N2Y5NyIsIm5iZiI6MTcyNDc3ODg2Ny4wMzkxNTEsInN1YiI6IjY2Y2Q5ZmYxN2NkOWY4ZmYwZTgxYTlmNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-15S7BbJtADBo3zQ1Axs8XxJezqeVuuIGqnHXQYnsmk"
}