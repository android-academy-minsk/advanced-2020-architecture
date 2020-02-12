package by.androidacademy.architecture.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import by.androidacademy.architecture.DetailsFragment
import by.androidacademy.architecture.api.response.MovieJson

class DetailsFragmentAdapter(
    fragmentManager: FragmentManager,
    private val movies: List<MovieJson>
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return DetailsFragment.newInstance(movies[position])
    }

    override fun getCount(): Int {
        return movies.size
    }
}