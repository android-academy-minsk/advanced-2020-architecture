package by.androidacademy.architecture.datasource

class MoviesDataSourceProvider(
    private val onlineDataSource: MoviesDataSource,
    private val localDataSource: MoviesDataSource
) {

    fun getDataSource(online: Boolean): MoviesDataSource {
        return if (online) {
            onlineDataSource
        } else {
            localDataSource
        }
    }
}