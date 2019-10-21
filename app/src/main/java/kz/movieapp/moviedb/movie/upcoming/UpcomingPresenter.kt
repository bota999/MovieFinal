package kz.movieapp.moviedb.movie.upcoming

interface UpcomingPresenter {
    fun setView(upcomingView: UpcomingView)
    fun loadMore(page: Int)
}