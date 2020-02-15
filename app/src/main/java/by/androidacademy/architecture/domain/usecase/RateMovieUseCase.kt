package by.androidacademy.architecture.domain.usecase

import by.androidacademy.architecture.domain.MoviesRepository
import by.androidacademy.architecture.domain.model.Movie

interface RateMovieUseCase {

    fun rate(movie: Movie, rating: Float)
}

class RateMovieUseCaseImpl(
    private val moviesRepository: MoviesRepository
) : RateMovieUseCase {

    override fun rate(movie: Movie, rating: Float) {
        moviesRepository.setMovieRating(movie.id, rating)
    }
}