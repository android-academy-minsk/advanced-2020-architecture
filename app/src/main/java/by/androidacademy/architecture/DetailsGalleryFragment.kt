package by.androidacademy.architecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import by.androidacademy.architecture.adapters.DetailsFragmentAdapter

class DetailsGalleryFragment : Fragment() {

    companion object {

        private const val ARGS_MOVIE_POSITION = "ARGS_MOVIE_POSITION"

        fun newInstance(
            position: Int
        ): DetailsGalleryFragment {
            val fragment =
                DetailsGalleryFragment()
            val bundle = Bundle()
            bundle.run {

                putInt(ARGS_MOVIE_POSITION, position)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_details_for_fragments, container, false)

        val position = arguments?.getInt(ARGS_MOVIE_POSITION) ?: 0

        view.findViewById<ViewPager>(R.id.vp_pager).run {
            adapter = DetailsFragmentAdapter(childFragmentManager)
            currentItem = position
        }

        return view
    }
}