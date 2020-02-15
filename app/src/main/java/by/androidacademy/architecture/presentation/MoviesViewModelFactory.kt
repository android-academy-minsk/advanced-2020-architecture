package by.androidacademy.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.androidacademy.architecture.domain.usecase.GetMoviesByQueryUseCase
import by.androidacademy.architecture.domain.usecase.GetPopularMoviesUseCase

class MoviesViewModelFactory(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMoviesByQueryUseCase: GetMoviesByQueryUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(getPopularMoviesUseCase, getMoviesByQueryUseCase) as T
    }
}