package by.androidacademy.architecture

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import by.androidacademy.architecture.adapters.MoviesAdapter
import by.androidacademy.architecture.api.RestService
import by.androidacademy.architecture.api.response.PopularMoviesResponse
import by.androidacademy.architecture.store.MoviesStore
import kotlinx.android.synthetic.main.activity_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    private lateinit var adapter: MoviesAdapter

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
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank()) {
                        showAllMovies()
                    } else {
                        showMoviesStartWith(newText)
                    }
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()

        if (MoviesStore.getMovies().isEmpty()) {
            loadMovies()
        } else {
            showAllMovies()
        }
    }

    private fun loadMovies() {
        showProgress()
        RestService.api.getPopularMovies().enqueue(object : Callback<PopularMoviesResponse?> {
            override fun onFailure(call: Call<PopularMoviesResponse?>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    t.message ?: "something went wrong",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onResponse(
                call: Call<PopularMoviesResponse?>,
                response: Response<PopularMoviesResponse?>
            ) {
                response.body()?.movies.orEmpty().also {
                    MoviesStore.putMovies(it)
                    hideProgress()
                    showAllMovies()
                }
            }
        })
    }

    private fun showProgress() {
        progress.show()
        recycler.isVisible = false
    }

    private fun hideProgress() {
        progress.hide()
        recycler.isVisible = true
    }

    private fun showAllMovies() {
        val movies = MoviesStore.getMovies()
        adapter.setMovies(movies)
    }

    private fun showMoviesStartWith(query: String) {
        val movies = MoviesStore.getMovies().filter { it.title.startsWith(query, true) }
        adapter.setMovies(movies)
    }

    private fun showDetailsFragment(selectedItemPosition: Int) {
        val detailsFragment = DetailsGalleryFragment.newInstance(selectedItemPosition)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, detailsFragment)
            .commit()
    }
}

