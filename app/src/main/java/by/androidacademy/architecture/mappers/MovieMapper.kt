package by.androidacademy.architecture.mappers

import by.androidacademy.architecture.api.ApiConstants
import by.androidacademy.architecture.api.response.MovieJson
import by.androidacademy.architecture.model.Movie

class MovieMapper : Mapper<MovieJson, Movie> {

    override fun map(from: MovieJson): Movie {

        return with(from) {
            Movie(
                id = id,
                title = title,
                description = overview,
                releaseDate = releaseDate,
                posterUrl = ApiConstants.POSTER_BASE_URL + posterPath,
                backdropUrl = ApiConstants.BACKDROP_BASE_URL + backdropPath
            )
        }
    }
}