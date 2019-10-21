package kz.movieapp.moviedb.detail

interface DetailPresenter {
    fun setView(detailView: DetailView, id: String)
}