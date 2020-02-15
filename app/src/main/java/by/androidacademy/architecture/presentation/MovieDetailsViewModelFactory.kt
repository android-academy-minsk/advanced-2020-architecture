package by.androidacademy.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.domain.usecase.GetMovieTrailerUseCase
import by.androidacademy.architecture.domain.usecase.RateMovieUseCase

class MovieDetailsViewModelFactory(
    private val movie: Movie,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    private val rateMovieUseCase: RateMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(movie, getMovieTrailerUseCase, rateMovieUseCase) as T
    }
}