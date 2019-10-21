package kz.movieapp.moviedb.search_history


import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_search_history.*
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.moviefilter.MovieFilterFragmentView
import kz.movieapp.moviedb.movie.moviefilter.MovieFilterPresenter
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchHistoryFragment(internal var query: String) : Fragment(), SearchHistoryFragmentView {

    lateinit var mapHistory: MutableMap<String, Movie>

    @Inject
    lateinit var presenter: SearchHistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as App).createMainComponent().inject(this)

    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mapHistory = getMoviesFromShared(context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_history, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        search_history_list.layoutManager = layoutManager
        search_history_list.setHasFixedSize(true)
        search_history_list.adapter = SearchHistoryAdapter()

        if (query.isEmpty()) {
            val list: List<Movie> = mapHistory.map { it.value }
            (search_history_list.adapter as SearchHistoryAdapter).addMovies(list.reversed())
        } else {
            presenter.searchMovie(this, query)
        }
    }

    fun getMoviesFromShared(context: Context): MutableMap<String, Movie> {
        val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
        val gson = Gson()
        val json = appSharedPrefs.getString("historyMoviesMp", null)
        val turnsType = object : TypeToken<MutableMap<String, Movie>>() {}.type
        val movieList = gson.fromJson<MutableMap<String, Movie>>(json, turnsType)

        val emptyList = mutableMapOf<String, Movie>()
        if (movieList == null)
            return emptyList
        else
            return movieList
    }


    override fun showSearchedResult(movieResponse: MovieResponse?) {
        (search_history_list.adapter as SearchHistoryAdapter).addMovies(movieResponse?.movies)
    }
}
