package by.androidacademy.architecture

import by.androidacademy.architecture.api.ApiDataSource
import by.androidacademy.architecture.cache.LocalDataSource
import by.androidacademy.architecture.cache.LocalRatingsDataSource
import by.androidacademy.architecture.data.MoviesRepositoryImpl
import by.androidacademy.architecture.data.MoviesDataSource
import by.androidacademy.architecture.data.RatingsDataSource
import by.androidacademy.architecture.data.mappers.MovieMapper
import by.androidacademy.architecture.data.mappers.MovieVideoMapper
import by.androidacademy.architecture.domain.MoviesRepository
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.domain.usecase.*
import by.androidacademy.architecture.presentation.MovieDetailsViewModelFactory
import by.androidacademy.architecture.presentation.MoviesViewModelFactory

object Dependencies {

    // Presentation
    val moviesViewModelFactory by lazy {
        createMoviesViewModelFactory(getPopularMoviesUseCase, getMoviesByQueryUseCase)
    }

    fun createMovieDetailsViewModelFactory(movie: Movie): MovieDetailsViewModelFactory {
        return MovieDetailsViewModelFactory(movie, getMovieTrailerUseCase, rateMovieUseCase)
    }

    // Domain
    val getPopularMoviesUseCase by lazy {
        createGetPopularMoviesUseCase(moviesRepository)
    }

    val getMoviesByQueryUseCase by lazy {
        createGetMoviesByQueryUseCase(moviesRepository)
    }

    val getMovieTrailerUseCase by lazy {
        createGetMovieTrailerUseCase(moviesRepository)
    }

    val rateMovieUseCase by lazy {
        createRateMovieUseCase(moviesRepository)
    }

    val moviesRepository by lazy {
        createMoviesRepository(onlineDataSource, localDataSource, createRatingsDataSource())
    }

    // Data
    val onlineDataSource by lazy {
        createOnlineDataSource()
    }

    val localDataSource by lazy {
        createLocalDataSource()
    }

    fun createRatingsDataSource(): RatingsDataSource {
        return LocalRatingsDataSource(MoviesApp.instance)
    }

    // Impls
    private fun createMoviesViewModelFactory(
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getMoviesByQueryUseCase: GetMoviesByQueryUseCase
    ): MoviesViewModelFactory {
        return MoviesViewModelFactory(getPopularMoviesUseCase, getMoviesByQueryUseCase)
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

    private fun createRateMovieUseCase(moviesRepository: MoviesRepository): RateMovieUseCase {
        return RateMovieUseCaseImpl(moviesRepository)
    }

    private fun createMoviesRepository(
        onlineDataSource: MoviesDataSource,
        localDataSource: MoviesDataSource,
        ratingsDataSource: RatingsDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            onlineDataSource = onlineDataSource,
            localDataSource = localDataSource,
            ratingsDataSource = ratingsDataSource,
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