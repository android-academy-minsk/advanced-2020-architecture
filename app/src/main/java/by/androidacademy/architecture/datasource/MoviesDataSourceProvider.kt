package by.androidacademy.architecture.datasource

class MoviesDataSourceProvider {

    private val onlineDataSource: MoviesDataSource = ApiDataSource()
    private val localDataSource: MoviesDataSource = LocalDataSource()

    fun getDataSource(online: Boolean): MoviesDataSource {
        return if (online) {
            onlineDataSource
        } else {
            localDataSource
        }
    }
}