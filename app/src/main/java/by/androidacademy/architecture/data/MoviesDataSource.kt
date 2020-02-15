package by.androidacademy.architecture.data

import by.androidacademy.architecture.api.response.MovieJson
import by.androidacademy.architecture.api.response.MovieVideoJson

interface MoviesDataSource {

    fun hasData(): Boolean

    fun getMovies(callback: (MoviesResult) -> Unit)

    fun getMoviesStartWith(query: String, callback: (MoviesResult) -> Unit)

    fun getMovieVideo(movieId: Int, callback: (MovieVideoJson?) -> Unit)
}

sealed class MoviesResult {

    data class Success(val movies: List<MovieJson>) : MoviesResult()

    data class Error(val message: String) : MoviesResult()
}