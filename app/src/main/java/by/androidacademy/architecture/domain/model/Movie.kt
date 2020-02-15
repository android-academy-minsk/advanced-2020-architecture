package by.androidacademy.architecture.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: String,
    val posterUrl: String,
    val backdropUrl: String
) : Parcelable {

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
        writeString(description)
        writeString(releaseDate)
        writeString(posterUrl)
        writeString(backdropUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }
}