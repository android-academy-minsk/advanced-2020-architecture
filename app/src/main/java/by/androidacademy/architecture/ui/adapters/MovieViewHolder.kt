package by.androidacademy.architecture.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.domain.model.Movie
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
