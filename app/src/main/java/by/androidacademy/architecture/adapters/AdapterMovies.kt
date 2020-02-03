package by.androidacademy.architecture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.R
import by.androidacademy.architecture.holders.HolderMoviesView
import by.androidacademy.architecture.providers.MoviesDataProvider

class AdapterMovies(
    context: Context,
    private val clickListener: (position: Int, resource: Int) -> Unit
) : RecyclerView.Adapter<HolderMoviesView>() {

    override fun onBindViewHolder(p0: HolderMoviesView, p1: Int) {
        p0.bind(p1, clickListener)
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HolderMoviesView {
        return HolderMoviesView(inflater.inflate(R.layout.item_movie, p0, false), clickListener)
    }

    override fun getItemCount(): Int {
        return MoviesDataProvider.getSize()
    }
}