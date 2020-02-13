package by.androidacademy.architecture.datasource

import by.androidacademy.architecture.mappers.MovieMapper
import by.androidacademy.architecture.store.MoviesStore

class LocalDataSource : MoviesDataSource {

    private val mapper = MovieMapper()

    override fun getMovies(callback: (Result) -> Unit) {
        MoviesStore.getMovies().map { mapper.map(it) }.also { movies ->
            callback(Result.Success(movies))
        }
    }

    override fun getMoviesStartWith(query: String, callback: (Result) -> Unit) {
        MoviesStore.getMovies().map { mapper.map(it) }
            .filter { it.title.startsWith(query, true) }
            .also { movies ->
                callback(Result.Success(movies))
            }
    }
}