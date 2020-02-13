package by.androidacademy.architecture.extensions

import androidx.appcompat.widget.SearchView

inline fun SearchView.doOnQueryTextSubmit(
    crossinline action: (query: String?) -> Boolean
) {
    setOnQueryTextListener(onQueryTextSubmit = action)
}

inline fun SearchView.doOnQueryTextChange(
    crossinline action: (newText: String?) -> Boolean
) {
    setOnQueryTextListener(onQueryTextChange = action)
}

inline fun SearchView.setOnQueryTextListener(
    crossinline onQueryTextSubmit: (query: String?) -> Boolean = { _ -> false },
    crossinline onQueryTextChange: (newText: String?) -> Boolean = { _ -> false }
) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return onQueryTextSubmit(query)
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return onQueryTextChange(newText)
        }
    })
}