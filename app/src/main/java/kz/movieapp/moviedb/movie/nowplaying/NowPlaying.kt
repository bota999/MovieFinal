package kz.movieapp.moviedb.movie.nowplaying


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.MovieAdapter
import kz.movieapp.moviedb.movie.PaginationRecycler
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NowPlaying(internal var from: String,internal var to: String, internal var request: String) : Fragment(), NowPlayingView {


    @Inject
    lateinit var presenter: NowPlayingPresenter

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
        presenter.setView(this, from, to, request)
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
                presenter.loadMore(from, to, request, page)
            }
        }
    }


    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }


    override fun showFilteredMovies(movieResponse: MovieResponse?) {
        progress_bar.visibility = View.GONE
        (list_movie.adapter as MovieAdapter).addMovies(movieResponse?.movies)
    }
}
