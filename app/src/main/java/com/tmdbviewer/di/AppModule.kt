package com.tmdbviewer.di

import com.tmdbviewer.common.data.ApiConstants
import com.tmdbviewer.common.data.AuthInterceptor
import com.tmdbviewer.common.data.MovieApi
import com.tmdbviewer.common.data.MovieRepository
import com.tmdbviewer.common.data.MovieRepositoryImpl
import com.tmdbviewer.common.providers.DefaultDispatcherProvider
import com.tmdbviewer.common.providers.DispatcherProvider
import com.tmdbviewer.moviedetail.domain.GetMovieDetailsUseCase
import com.tmdbviewer.movielist.domain.GetLatestMoviesUseCase
import com.tmdbviewer.movielist.domain.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideAuthInterceptor(): AuthInterceptor {
    return AuthInterceptor()
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {

    val logging = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
      .addInterceptor(authInterceptor)
      .addInterceptor(logging)
      .build()
  }

  @Provides
  @Singleton
  fun provideDispatcherProvider(): DispatcherProvider {
    return DefaultDispatcherProvider()
  }


  @Provides
  @Singleton
  fun provideMovieApi(okHttpClient: OkHttpClient): MovieApi {

    return Retrofit.Builder()
      .baseUrl(ApiConstants.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(MovieApi::class.java)
  }

  @Provides
  @Singleton
  fun provideMovieRepository(
    movieApi: MovieApi,
    dispatcherProvider: DispatcherProvider
  ): MovieRepository {
    return MovieRepositoryImpl(movieApi, dispatcherProvider)
  }

  @Provides
  @Singleton
  fun provideSearchMovieUseCase(repository: MovieRepository): SearchMoviesUseCase {
    return SearchMoviesUseCase(repository)
  }

  @Provides
  @Singleton
  fun provideGetLatestMovieUseCase(repository: MovieRepository): GetLatestMoviesUseCase {
    return GetLatestMoviesUseCase(repository)
  }

  @Provides
  @Singleton
  fun provideMovieDetailUseCase(repository: MovieRepository): GetMovieDetailsUseCase {
    return GetMovieDetailsUseCase(repository)
  }
}