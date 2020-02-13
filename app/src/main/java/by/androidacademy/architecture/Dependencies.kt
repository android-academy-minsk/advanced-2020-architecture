package by.androidacademy.architecture

import by.androidacademy.architecture.datasource.ApiDataSource
import by.androidacademy.architecture.datasource.LocalDataSource
import by.androidacademy.architecture.datasource.MoviesDataSource

object Dependencies {

    val onlineDataSource by lazy {
        createOnlineDataSource()
    }

    val localDataSource by lazy {
        createLocalDataSource()
    }

    private fun createOnlineDataSource(): MoviesDataSource {
        return ApiDataSource()
    }

    private fun createLocalDataSource(): MoviesDataSource {
        return LocalDataSource()
    }
}