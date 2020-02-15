package by.androidacademy.architecture.data

interface RatingsDataSource {

    fun getRating(movieId: Int): Float

    fun setRating(movieId: Int, rating: Float)
}