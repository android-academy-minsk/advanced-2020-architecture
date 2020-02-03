package by.androidacademy.architecture.threads_part


import android.content.Context
import androidx.annotation.StringRes

class StringsProvider(val context: Context) {

    fun getString(@StringRes stringResId: Int) = context.getString(stringResId)
}