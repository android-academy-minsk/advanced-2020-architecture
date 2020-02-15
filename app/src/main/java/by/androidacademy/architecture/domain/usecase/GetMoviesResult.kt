package by.androidacademy.architecture.domain.usecase

import by.androidacademy.architecture.domain.model.Movie

sealed class GetMoviesResult {

    data class Success(val movies: List<Movie>) : GetMoviesResult()

    data class Error(val message: String) : GetMoviesResult()
}