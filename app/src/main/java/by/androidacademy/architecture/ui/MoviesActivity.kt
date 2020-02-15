package by.androidacademy.architecture.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import by.androidacademy.architecture.Dependencies
import by.androidacademy.architecture.R
import by.androidacademy.architecture.ui.adapters.MoviesAdapter
import by.androidacademy.architecture.domain.usecase.GetMoviesByQueryUseCase
import by.androidacademy.architecture.domain.usecase.GetMoviesResult
import by.androidacademy.architecture.domain.usecase.GetPopularMoviesUseCase
import by.androidacademy.architecture.ui.extensions.doOnQueryTextChange
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesActivity : AppCompatActivity() {

    private lateinit var adapter: MoviesAdapter

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase =
        Dependencies.getPopularMoviesUseCase

    private val getMoviesByQueryUseCase: GetMoviesByQueryUseCase =
        Dependencies.getMoviesByQueryUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        adapter = MoviesAdapter { itemPosition ->
            showDetailsFragment(itemPosition)
        }
        recycler.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movies, menu)
        val searchItem = menu?.findItem(R.id.appSearchBar)
        (searchItem?.actionView as? SearchView)?.let { searchView ->
            searchView.queryHint = "Search"
            searchView.doOnQueryTextChange { newText ->
                showMoviesStartWith(newText.orEmpty())
                return@doOnQueryTextChange true
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()

        showProgress()
        getPopularMoviesUseCase
            .getMovies { result ->
                when (result) {
                    is GetMoviesResult.Success -> {
                        hideProgress()
                        adapter.setMovies(result.movies)
                    }
                    is GetMoviesResult.Error -> {
                        Toast.makeText(
                            applicationContext,
                            result.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun showProgress() {
        progress.show()
        recycler.isVisible = false
    }

    private fun hideProgress() {
        progress.hide()
        recycler.isVisible = true
    }

    private fun showMoviesStartWith(query: String) {
        getMoviesByQueryUseCase
            .getMovies(query) { result ->
                when (result) {
                    is GetMoviesResult.Success -> {
                        adapter.setMovies(result.movies)
                    }
                    is GetMoviesResult.Error -> {
                        Toast.makeText(
                            applicationContext,
                            result.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun showDetailsFragment(selectedItemPosition: Int) {
        val detailsFragment =
            DetailsGalleryFragment.newInstance(
                selectedItemPosition
            )
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, detailsFragment)
            .commit()
    }
}

