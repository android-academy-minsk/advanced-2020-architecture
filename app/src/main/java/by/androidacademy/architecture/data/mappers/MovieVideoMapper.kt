package by.androidacademy.architecture.data.mappers

import by.androidacademy.architecture.api.ApiConstants
import by.androidacademy.architecture.api.response.MovieVideoJson
import by.androidacademy.architecture.domain.model.MovieVideo
import by.androidacademy.architecture.domain.mappers.Mapper

class MovieVideoMapper : Mapper<MovieVideoJson, MovieVideo> {

    override fun map(from: MovieVideoJson): MovieVideo {
        return with(from) {
            MovieVideo(videoUrl = ApiConstants.YOUTUBE_BASE_URL + key)
        }
    }
}