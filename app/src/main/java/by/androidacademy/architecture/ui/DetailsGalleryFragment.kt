package by.androidacademy.architecture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.androidacademy.architecture.Dependencies
import by.androidacademy.architecture.R
import by.androidacademy.architecture.ui.adapters.DetailsFragmentAdapter
import by.androidacademy.architecture.domain.usecase.GetMoviesResult
import by.androidacademy.architecture.domain.usecase.GetPopularMoviesUseCase
import kotlinx.android.synthetic.main.fragment_movies_gallery.*

class DetailsGalleryFragment : Fragment() {

    companion object {

        private const val ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION"

        fun newInstance(position: Int): DetailsGalleryFragment {
            return DetailsGalleryFragment().apply {
                arguments = bundleOf(ARGS_MOVIE_POSITION to position)
            }
        }
    }

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase =
        Dependencies.getPopularMoviesUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARGS_MOVIE_POSITION) ?: 0

        getPopularMoviesUseCase.getMovies { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    vp_pager.run {
                        adapter = DetailsFragmentAdapter(childFragmentManager, result.movies)
                        currentItem = position
                    }
                }
                is GetMoviesResult.Error -> {
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