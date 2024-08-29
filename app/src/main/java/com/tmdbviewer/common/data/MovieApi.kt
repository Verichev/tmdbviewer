package com.tmdbviewer.common.data

import com.tmdbviewer.moviedetail.data.MovieDetailResponse
import com.tmdbviewer.movielist.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

  @GET("discover/movie")
  suspend fun getMovies(
    @Query("page") page: Int,
    @Query("primary_release_date.lte") primaryReleaseDateLte: String,
    @Query("include_adult") includeAdult: Boolean = false,
    @Query("include_video") includeVideo: Boolean = false,
    @Query("language") language: String = "en-US",
    @Query("sort_by") sortBy: String = "primary_release_date.desc"
  ): MovieResponse

  @GET("search/movie")
  suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): MovieResponse

  @GET("movie/{movie_id}")
  suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailResponse
}