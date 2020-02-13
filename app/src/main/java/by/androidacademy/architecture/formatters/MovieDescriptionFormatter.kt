package by.androidacademy.architecture.formatters

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import by.androidacademy.architecture.api.response.MovieJson

class MovieDescriptionFormatter {

    fun format(movieJson: MovieJson): Spannable {
        val overview = movieJson.overview
        return SpannableStringBuilder(overview).apply {
            setSpan(
                BackgroundColorSpan(Color.CYAN),
                0,
                overview.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}