package by.androidacademy.architecture.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.androidacademy.architecture.Dependencies
import by.androidacademy.architecture.R
import by.androidacademy.architecture.presentation.MoviesViewModel
import by.androidacademy.architecture.presentation.MoviesViewModelFactory
import by.androidacademy.architecture.ui.adapters.DetailsFragmentAdapter
import kotlinx.android.synthetic.main.fragment_movies_gallery.*

class DetailsGalleryFragment : Fragment(R.layout.fragment_movies_gallery) {

    companion object {

        private const val ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION"

        fun newInstance(position: Int): DetailsGalleryFragment {
            return DetailsGalleryFragment().apply {
                arguments = bundleOf(ARGS_MOVIE_POSITION to position)
            }
        }
    }

    private val viewModelFactory: MoviesViewModelFactory = Dependencies.moviesViewModelFactory

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MoviesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(ARGS_MOVIE_POSITION) ?: 0

        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            vp_pager.run {
                adapter = DetailsFragmentAdapter(childFragmentManager, movies)
                currentItem = position
            }
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })
    }
}