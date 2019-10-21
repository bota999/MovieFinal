package kz.movieapp.moviedb.search_history

import kz.movieapp.moviedb.models.response.MovieResponse

interface SearchHistoryFragmentView {
    fun showSearchedResult(movieResponse: MovieResponse?)
}