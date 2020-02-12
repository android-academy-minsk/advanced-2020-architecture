package by.androidacademy.architecture.api.response

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import com.google.gson.annotations.SerializedName

data class MovieJson(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
) : Parcelable {

    fun formatDescription(): Spannable {
        return SpannableStringBuilder(overview).apply {
            setSpan(
                BackgroundColorSpan(Color.CYAN),
                0,
                overview.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString().orEmpty(),
        source.readString().orEmpty(),
        source.readString().orEmpty(),
        source.readString().orEmpty(),
        source.readString().orEmpty()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(overview)
        writeString(releaseDate)
        writeString(posterPath)
        writeString(backdropPath)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieJson> = object : Parcelable.Creator<MovieJson> {
            override fun createFromParcel(source: Parcel): MovieJson = MovieJson(source)
            override fun newArray(size: Int): Array<MovieJson?> = arrayOfNulls(size)
        }
    }
}