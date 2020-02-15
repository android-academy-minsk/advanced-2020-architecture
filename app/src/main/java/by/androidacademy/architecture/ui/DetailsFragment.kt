package by.androidacademy.architecture.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.androidacademy.architecture.Dependencies
import by.androidacademy.architecture.R
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.domain.usecase.GetMovieTrailerUseCase
import by.androidacademy.architecture.domain.usecase.GetTrailerResult
import by.androidacademy.architecture.ui.formatters.MovieDescriptionFormatter
import coil.api.load
import kotlinx.android.synthetic.main.fragment_movie_details.*

class DetailsFragment : Fragment() {

    companion object {

        private const val ARG_MOVIE = "arg.movie"

        fun newInstance(movie: Movie): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ARG_MOVIE to movie)
            }
        }
    }

    private lateinit var movie: Movie

    private val getMovieTrailerUseCase: GetMovieTrailerUseCase =
        Dependencies.getMovieTrailerUseCase
    private val descriptionFormatter = MovieDescriptionFormatter()

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
            ivBackground.load(backdropUrl)
            ivPoster.load(posterUrl)
            tvTitle.text = title
            tvReleasedDate.text = releaseDate
            tvOverview.text = descriptionFormatter.format(this)
        }

        btnTrailer.setOnClickListener {
            getMovieTrailerUseCase.getTrailer(movie.id) { result ->
                when (result) {
                    is GetTrailerResult.Success -> {
                        openMovieTrailer(result.movieVideo.videoUrl)
                    }
                    is GetTrailerResult.Error -> {
                        Toast.makeText(
                            requireContext(),
                            result.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun openMovieTrailer(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}