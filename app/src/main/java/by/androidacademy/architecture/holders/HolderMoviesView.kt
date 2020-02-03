package by.androidacademy.architecture.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_DESCRIPTION
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_IMAGE
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_IMAGE_URL
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_TITLE
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.getResource
import kotlinx.android.synthetic.main.item_movie.view.*

class HolderMoviesView(
    itemView: View,
    clickListener: (position: Int, resource: Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int, listener: (position: Int, resource: Int) -> Unit) = with(itemView) {
        txt_name.text = context.getText(getResource(TYPE_TITLE, position))
        txt_review.text = context.getText(getResource(TYPE_DESCRIPTION, position))
        img_image.setImageDrawable(context.getDrawable(getResource(TYPE_IMAGE, position)))
        setOnClickListener { listener(position, getResource(TYPE_IMAGE_URL, position)) }
    }
}
