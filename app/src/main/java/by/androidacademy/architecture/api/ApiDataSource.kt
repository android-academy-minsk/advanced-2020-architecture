package by.androidacademy.architecture.api

import by.androidacademy.architecture.api.response.MovieVideoJson
import by.androidacademy.architecture.api.response.MovieVideosResponse
import by.androidacademy.architecture.api.response.PopularMoviesResponse
import by.androidacademy.architecture.data.MoviesDataSource
import by.androidacademy.architecture.data.MoviesResult
import by.androidacademy.architecture.cache.MoviesStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSource : MoviesDataSource {

    override fun hasData(): Boolean = false

    override fun getMovies(callback: (MoviesResult) -> Unit) {
        RestService.api.getPopularMovies().enqueue(object : Callback<PopularMoviesResponse?> {
            override fun onFailure(call: Call<PopularMoviesResponse?>, t: Throwable) {
                callback(
                    MoviesResult.Error(
                        t.message ?: "something went wrong"
                    )
                )
            }

            override fun onResponse(
                call: Call<PopularMoviesResponse?>,
                response: Response<PopularMoviesResponse?>
            ) {
                response.body()?.movies.orEmpty().also { moviesJson ->
                    MoviesStore.putMovies(moviesJson) // fixme: dirty hack
                    callback(
                        MoviesResult.Success(moviesJson)
                    )
                }
            }
        })
    }

    override fun getMoviesStartWith(query: String, callback: (MoviesResult) -> Unit) {
        // TODO: implement api request
        callback(
            MoviesResult.Success(emptyList())
        )
    }

    override fun getMovieVideo(movieId: Int, callback: (MovieVideoJson?) -> Unit) {
        RestService.api.getMovieVideos(movieId)
            .enqueue(object : Callback<MovieVideosResponse?> {
                override fun onFailure(call: Call<MovieVideosResponse?>, t: Throwable) {
                    callback(null)
                }

                override fun onResponse(
                    call: Call<MovieVideosResponse?>,
                    response: Response<MovieVideosResponse?>
                ) {
                    response.body()?.videos?.first().let { video ->
                        callback(video)
                    }
                }
            })
    }
}