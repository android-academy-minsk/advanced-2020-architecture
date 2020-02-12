package by.androidacademy.architecture.api

import by.androidacademy.architecture.api.response.MovieVideosResponse
import by.androidacademy.architecture.api.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = ApiConstants.SERVER_API_KEY
    ): Call<PopularMoviesResponse>

    @GET("movie/{movieId}/videos")
    fun getMovieVideos(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String = ApiConstants.SERVER_API_KEY
    ): Call<MovieVideosResponse>
}