package by.androidacademy.architecture.cache

import by.androidacademy.architecture.api.response.MovieVideoJson
import by.androidacademy.architecture.data.MoviesDataSource
import by.androidacademy.architecture.data.MoviesResult

class LocalDataSource : MoviesDataSource {

    override fun hasData(): Boolean {
        return MoviesStore.getMovies().isNotEmpty()
    }

    override fun getMovies(callback: (MoviesResult) -> Unit) {
        MoviesStore.getMovies().also { movies ->
            callback(MoviesResult.Success(movies))
        }
    }

    override fun getMoviesStartWith(query: String, callback: (MoviesResult) -> Unit) {
        MoviesStore.getMovies()
            .filter { it.title.startsWith(query, true) }
            .also { movies ->
                callback(MoviesResult.Success(movies))
            }
    }

    override fun getMovieVideo(movieId: Int, callback: (MovieVideoJson?) -> Unit) {
        // todo: implement
        callback(null)
    }
}