package by.androidacademy.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.androidacademy.architecture.adapters.DetailsFragmentAdapter
import by.androidacademy.architecture.store.MoviesStore
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
        val movies = MoviesStore.getMovies()

        vp_pager.run {
            adapter = DetailsFragmentAdapter(childFragmentManager, movies)
            currentItem = position
        }
    }
}