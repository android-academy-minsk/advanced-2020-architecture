package by.androidacademy.architecture

import by.androidacademy.architecture.api.ApiDataSource
import by.androidacademy.architecture.cache.LocalDataSource
import by.androidacademy.architecture.data.MoviesRepositoryImpl
import by.androidacademy.architecture.data.MoviesDataSource
import by.androidacademy.architecture.data.mappers.MovieMapper
import by.androidacademy.architecture.data.mappers.MovieVideoMapper
import by.androidacademy.architecture.domain.MoviesRepository
import by.androidacademy.architecture.domain.usecase.*

object Dependencies {

    val getPopularMoviesUseCase by lazy {
        createGetPopularMoviesUseCase(moviesRepository)
    }

    val getMoviesByQueryUseCase by lazy {
        createGetMoviesByQueryUseCase(moviesRepository)
    }

    val getMovieTrailerUseCase by lazy {
        createGetMovieTrailerUseCase(moviesRepository)
    }

    val moviesRepository by lazy {
        createMoviesRepository(onlineDataSource, localDataSource)
    }

    val onlineDataSource by lazy {
        createOnlineDataSource()
    }

    val localDataSource by lazy {
        createLocalDataSource()
    }

    private fun createGetPopularMoviesUseCase(moviesRepository: MoviesRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(moviesRepository)
    }

    private fun createGetMoviesByQueryUseCase(moviesRepository: MoviesRepository): GetMoviesByQueryUseCase {
        return GetMoviesByQueryUseCaseImpl(moviesRepository)
    }

    private fun createGetMovieTrailerUseCase(moviesRepository: MoviesRepository): GetMovieTrailerUseCase {
        return GetMovieTrailerUseCaseImpl(moviesRepository)
    }

    private fun createMoviesRepository(
        onlineDataSource: MoviesDataSource,
        localDataSource: MoviesDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            onlineDataSource = onlineDataSource,
            localDataSource = localDataSource,
            movieMapper = MovieMapper(),
            movieVideoMapper = MovieVideoMapper()
        )
    }

    private fun createOnlineDataSource(): MoviesDataSource {
        return ApiDataSource()
    }

    private fun createLocalDataSource(): MoviesDataSource {
        return LocalDataSource()
    }
}