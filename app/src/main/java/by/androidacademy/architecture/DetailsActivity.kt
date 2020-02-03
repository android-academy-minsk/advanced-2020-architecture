package by.androidacademy.architecture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import by.androidacademy.architecture.helpers.GoToUrlHelper
import by.androidacademy.architecture.providers.MoviesDataProvider
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_DESCRIPTION
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_IMAGE
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_IMAGE_URL
import by.androidacademy.architecture.providers.MoviesDataProvider.Companion.TYPE_TITLE
import kotlinx.android.synthetic.main.activity_details_constraint.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details_constraint)

        val title = intent?.getIntExtra(MOVIE_TITLE, 0)
        val description = intent?.getIntExtra(MOVIE_DESCRIPTION, 0)
        val image = intent?.getIntExtra(MOVIE_IMAGE, 0)
        val url = intent?.getIntExtra(MOVIE_URL, 0)
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

        findViewById<Button>(R.id.btn_to_movie).setOnClickListener(View.OnClickListener {
            if (url != null) {
                GoToUrlHelper.gotoUrl(
                    this, url
                )
            }
        })
    }

    companion object {

        const val MOVIE_TITLE = "MOVIE_TITLE"
        const val MOVIE_DESCRIPTION = "MOVIE_DESCRIPTION"
        const val MOVIE_IMAGE = "MOVIE_IMAGE"
        const val MOVIE_URL = "MOVIE_URL"

        fun createIntent(context: Context, position: Int): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MOVIE_TITLE, MoviesDataProvider.getResource(TYPE_TITLE, position))
            intent.putExtra(
                MOVIE_DESCRIPTION,
                MoviesDataProvider.getResource(TYPE_DESCRIPTION, position)
            )
            intent.putExtra(MOVIE_IMAGE, MoviesDataProvider.getResource(TYPE_IMAGE, position))
            intent.putExtra(MOVIE_URL, MoviesDataProvider.getResource(TYPE_IMAGE_URL, position))
            return intent
        }
    }
}
