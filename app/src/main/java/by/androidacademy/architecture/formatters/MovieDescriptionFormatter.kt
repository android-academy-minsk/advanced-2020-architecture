package by.androidacademy.architecture.formatters

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import by.androidacademy.architecture.model.Movie

class MovieDescriptionFormatter {

    fun format(movieJson: Movie): Spannable {
        val description = movieJson.description
        return SpannableStringBuilder(description).apply {
            setSpan(
                BackgroundColorSpan(Color.CYAN),
                0,
                description.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}