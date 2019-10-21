package kz.movieapp.moviedb.movie.popularmovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.coroutines.*
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.movie.MovieAdapter
import kz.movieapp.moviedb.movie.PaginationRecycler
import kz.movieapp.moviedb.movie.popularmovies.PopularMoviesPresenter
import kz.movieapp.moviedb.movie.popularmovies.PopularMoviesView
import javax.inject.Inject


class PopularMovies : Fragment(), PopularMoviesView {

    @Inject
    lateinit var presenter: PopularMoviesPresenter

    lateinit var paginationRecycler: PaginationRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as App).createMainComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        presenter.setView(this)
    }

    private fun initLayout() {
        list_movie.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 3)
        list_movie.layoutManager = layoutManager
        list_movie.setHasFixedSize(true)
        list_movie.adapter = MovieAdapter(context)

        paginationRecycler = object : PaginationRecycler(layoutManager) {
            override fun nextPage(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadNextDataFromApi(page)
            }
        }
        list_movie.addOnScrollListener(paginationRecycler)

    }

    private fun loadNextDataFromApi(page: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            showLoading()
            withContext(Dispatchers.IO) {
                presenter.loadMore(page)
            }
        }
    }


    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun showPopularMovies(movies: ArrayList<Movie>?) {
        progress_bar.visibility = View.GONE
        (list_movie.adapter as MovieAdapter).addMovies(movies)
    }
}
