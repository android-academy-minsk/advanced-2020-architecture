package by.androidacademy.architecture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.androidacademy.architecture.api.ApiConstants
import by.androidacademy.architecture.api.RestService
import by.androidacademy.architecture.api.response.MovieJson
import by.androidacademy.architecture.api.response.MovieVideosResponse
import coil.api.load
import kotlinx.android.synthetic.main.fragment_movie_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : Fragment() {

    companion object {

        private const val ARG_MOVIE = "arg.movie"

        fun newInstance(movie: MovieJson): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ARG_MOVIE to movie)
            }
        }
    }

    private lateinit var movie: MovieJson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie = arguments?.getParcelable(ARG_MOVIE)
            ?: throw IllegalArgumentException("missing argument!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(movie) {
            ivBackground.load(ApiConstants.BACKDROP_BASE_URL + backdropPath)
            ivPoster.load(ApiConstants.POSTER_BASE_URL + posterPath)
            tvTitle.text = title
            tvReleasedDate.text = releaseDate
            tvOverview.text = formatDescription()
        }

        btnTrailer.setOnClickListener {
            RestService.api.getMovieVideos(movie.id)
                .enqueue(object : Callback<MovieVideosResponse?> {
                    override fun onFailure(call: Call<MovieVideosResponse?>, t: Throwable) {
                        Toast.makeText(
                            requireContext(),
                            t.message ?: "failed to load trailer",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        call: Call<MovieVideosResponse?>,
                        response: Response<MovieVideosResponse?>
                    ) {
                        response.body()?.videos?.first()?.let {
                            openMovieTrailer(ApiConstants.YOUTUBE_BASE_URL + it.key)
                        }
                    }
                })
        }
    }

    private fun openMovieTrailer(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}