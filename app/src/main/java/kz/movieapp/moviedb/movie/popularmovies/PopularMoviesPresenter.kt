package kz.movieapp.moviedb.movie.popularmovies

interface PopularMoviesPresenter {
    fun setView(popularMoviesView: PopularMoviesView)
    fun loadMore(page: Int)
}