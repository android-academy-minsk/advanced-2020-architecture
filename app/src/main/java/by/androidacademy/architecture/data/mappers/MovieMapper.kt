package by.androidacademy.architecture.data.mappers

import by.androidacademy.architecture.api.ApiConstants
import by.androidacademy.architecture.api.response.MovieJson
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.domain.mappers.Mapper

class MovieMapper : Mapper<MovieJson, Movie> {

    override fun map(from: MovieJson): Movie {

        return with(from) {
            Movie(
                id = id,
                title = title,
                description = overview,
                releaseDate = releaseDate,
                posterUrl = ApiConstants.POSTER_BASE_URL + posterPath,
                backdropUrl = ApiConstants.BACKDROP_BASE_URL + backdropPath,
                rating = 0.0f
            )
        }
    }
}