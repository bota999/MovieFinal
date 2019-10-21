package kz.movieapp.moviedb.search_history

interface SearchHistoryPresenter {
    fun searchMovie(view: SearchHistoryFragment, query: String)
}