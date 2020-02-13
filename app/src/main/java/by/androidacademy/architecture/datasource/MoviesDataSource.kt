package by.androidacademy.architecture.datasource

import by.androidacademy.architecture.model.Movie

interface MoviesDataSource {

    fun getMovies(callback: (Result) -> Unit)

    fun getMoviesStartWith(query: String, callback: (Result) -> Unit)
}

sealed class Result {

    data class Success(val movies: List<Movie>) : Result()

    data class Error(val message: String) : Result()
}