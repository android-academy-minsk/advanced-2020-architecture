package by.androidacademy.architecture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.R
import by.androidacademy.architecture.domain.model.Movie

class MoviesAdapter(private val clickListener: (itemPosition: Int) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = emptyList()

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position]) {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}