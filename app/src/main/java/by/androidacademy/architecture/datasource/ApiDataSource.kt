package by.androidacademy.architecture.datasource

import by.androidacademy.architecture.api.RestService
import by.androidacademy.architecture.api.response.PopularMoviesResponse
import by.androidacademy.architecture.mappers.MovieMapper
import by.androidacademy.architecture.store.MoviesStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDataSource : MoviesDataSource {

    private val mapper = MovieMapper()

    override fun getMovies(callback: (Result) -> Unit) {
        RestService.api.getPopularMovies().enqueue(object : Callback<PopularMoviesResponse?> {
            override fun onFailure(call: Call<PopularMoviesResponse?>, t: Throwable) {
                callback(Result.Error(
                    t.message ?: "something went wrong"
                ))
            }

            override fun onResponse(
                call: Call<PopularMoviesResponse?>,
                response: Response<PopularMoviesResponse?>
            ) {
                response.body()?.movies.orEmpty().also { moviesJson ->
                    MoviesStore.putMovies(moviesJson)
                    callback(Result.Success(moviesJson.map { json ->
                        mapper.map(json)
                    }))
                }
            }
        })
    }

    override fun getMoviesStartWith(query: String, callback: (Result) -> Unit) {
        TODO("not implemented")
    }
}