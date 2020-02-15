package by.androidacademy.architecture.cache

import android.content.Context
import androidx.core.content.edit
import by.androidacademy.architecture.data.RatingsDataSource

private const val RATINGS_PREFS = "local.ratings"

class LocalRatingsDataSource(
    appContext: Context
) : RatingsDataSource {

    private val sharedPreferences =
        appContext.getSharedPreferences(RATINGS_PREFS, Context.MODE_PRIVATE)

    override fun getRating(movieId: Int): Float {
        return sharedPreferences.getFloat(movieId.toString(), 0.0f)
    }

    override fun setRating(movieId: Int, rating: Float) {
        sharedPreferences.edit {
            putFloat(movieId.toString(), rating)
        }
    }
}