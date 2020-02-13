package by.androidacademy.architecture.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.model.Movie
import coil.api.load
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movie: Movie, onClick: () -> Unit) {
        with(itemView) {
            with(movie) {
                tvTitle.text = title
                tvOverview.text = description
                ivPoster.load(posterUrl)
            }
            setOnClickListener { onClick() }
        }
    }
}
