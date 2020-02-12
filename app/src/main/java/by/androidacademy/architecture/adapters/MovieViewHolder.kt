package by.androidacademy.architecture.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.api.ApiConstants
import by.androidacademy.architecture.api.response.MovieJson
import coil.api.load
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: MovieJson, onClick: () -> Unit) {
        with(itemView) {
            with(movie) {
                tvTitle.text = title
                tvOverview.text = overview
                ivPoster.load(ApiConstants.POSTER_BASE_URL + posterPath)
            }
            setOnClickListener { onClick() }
        }
    }
}
