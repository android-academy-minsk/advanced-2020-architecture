package by.androidacademy.architecture.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.androidacademy.architecture.Dependencies
import by.androidacademy.architecture.R
import by.androidacademy.architecture.domain.model.Movie
import by.androidacademy.architecture.presentation.MovieDetailsViewModel
import by.androidacademy.architecture.ui.formatters.MovieDescriptionFormatter
import coil.api.load
import kotlinx.android.synthetic.main.fragment_movie_details.*

class DetailsFragment : Fragment(R.layout.fragment_movie_details) {

    companion object {

        private const val ARG_MOVIE = "arg.movie"

        fun newInstance(movie: Movie): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ARG_MOVIE to movie)
            }
        }
    }

    private lateinit var viewModel: MovieDetailsViewModel

    private val descriptionFormatter = MovieDescriptionFormatter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movie: Movie = arguments?.getParcelable(ARG_MOVIE)
            ?: throw IllegalArgumentException("missing argument!")

        val viewModelFactory = Dependencies.createMovieDetailsViewModelFactory(movie)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModelObservers()

        btnTrailer.setOnClickListener {
            viewModel.loadTrailer()
        }
    }

    private fun initViewModelObservers() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movie ->
            with(movie) {
                ivBackground.load(backdropUrl)
                ivPoster.load(posterUrl)
                tvTitle.text = title
                tvReleasedDate.text = releaseDate
                tvOverview.text = descriptionFormatter.format(this)
            }
        })
        viewModel.movieTrailerLiveData.observe(viewLifecycleOwner, Observer { movieVideo ->
            openMovieTrailer(movieVideo.videoUrl)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })
    }

    private fun openMovieTrailer(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}