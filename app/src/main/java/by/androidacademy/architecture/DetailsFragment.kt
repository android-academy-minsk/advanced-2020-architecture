package by.androidacademy.architecture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import by.androidacademy.architecture.helpers.GoToUrlHelper
import by.androidacademy.architecture.providers.MoviesDataProvider
import kotlinx.android.synthetic.main.activity_details_constraint.*

class DetailsFragment : Fragment() {

    companion object {

        fun newInstance(position: Int): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putInt(
                DetailsActivity.MOVIE_TITLE, MoviesDataProvider.getResource(
                    MoviesDataProvider.TYPE_TITLE, position
                )
            )
            bundle.putInt(
                DetailsActivity.MOVIE_DESCRIPTION,
                MoviesDataProvider.getResource(MoviesDataProvider.TYPE_DESCRIPTION, position)
            )
            bundle.putInt(
                DetailsActivity.MOVIE_IMAGE, MoviesDataProvider.getResource(
                    MoviesDataProvider.TYPE_IMAGE, position
                )
            )
            bundle.putInt(
                DetailsActivity.MOVIE_URL, MoviesDataProvider.getResource(
                    MoviesDataProvider.TYPE_IMAGE_URL, position
                )
            )
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_details_constraint, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getInt(DetailsActivity.MOVIE_TITLE, 0)
        val description = arguments?.getInt(DetailsActivity.MOVIE_DESCRIPTION, 0)
        val image = arguments?.getInt(DetailsActivity.MOVIE_IMAGE, 0)
        val url = arguments?.getInt(DetailsActivity.MOVIE_URL, 0)
        //TODO Strange construction here
        if (title == null || description == null || image == null || url == null) {
            throw IllegalArgumentException("Missing argument")
        }
        if (title == 0 || description == 0 || image == 0 || url == 0) {
            throw IllegalArgumentException("Missing argument")
        }
        txt_top_title.setText(title)
        txt_small_right.setText(R.string.unknown)
        txt_bottom.setText(description)
        img_center.setImageResource(image)

        view.findViewById<Button>(R.id.btn_to_movie).setOnClickListener(View.OnClickListener {
            if (url != null) {
                GoToUrlHelper.gotoUrl(
                    view.context, url
                )
            }
        })

    }

    private fun openMovieTrailer(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


}