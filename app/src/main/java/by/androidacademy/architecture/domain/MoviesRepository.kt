package by.androidacademy.architecture.domain

import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.domain.model.MovieVideo

interface MoviesRepository {

    fun getPopularMovies(callback: RepositoryCallback)

    fun getMoviesStartWith(query: String, callback: RepositoryCallback)

    fun getMovieTrailer(movieId: Int, callback: (MovieVideo?) -> Unit)

    fun setMovieRating(movieId: Int, rating: Float)
}

interface RepositoryCallback {

    fun onSuccess(movies: List<Movie>)

    fun onError(message: String)
}