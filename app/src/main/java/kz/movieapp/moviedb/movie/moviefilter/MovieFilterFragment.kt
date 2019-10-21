package kz.movieapp.moviedb.movie.moviefilter


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_movie_filter.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.models.response.MovieResponse
import kz.movieapp.moviedb.movie.moviefilter.activity.MovieFilterActivity
import kz.movieapp.moviedb.movie.moviefilter.widgets.GenreListAdapter
import kz.movieapp.moviedb.movie.moviefilter.widgets.GenreListViewDialog
import javax.inject.Inject

class MovieFilterFragment : Fragment(), DoneItemClickListener, MovieFilterFragmentView {

    lateinit var customDialog: Dialog
    var checkContainer: BooleanArray = BooleanArray(50)
    var genreList = GenreList(arrayListOf())
    var request = ""

    @Inject
    lateinit var presenter: MovieFilterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as App).createMainComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    private fun initLayout() {
        genre_filter_btn.setOnClickListener {
            GlobalScope.launch {
                val w1 = GlobalScope.async {
                    presenter.prepareGenreList(this@MovieFilterFragment)
                }
                w1.await()
            }
        }

        filter_search_btn.setOnClickListener{
            val from = filter_from_et.text.toString()
            val to = filter_to_et.text.toString()
//            presenter.filterMovie(from, to, request)
            val activity = Intent(context,MovieFilterActivity::class.java)
            activity.putExtra("toKey", to)
            activity.putExtra("fromKey", from)
            activity.putExtra("requestKey", request)
            startActivity(activity)
        }
    }

    override fun onDoneClick(data: BooleanArray) {
        request = ""
        var str = ""
        if (data.size > 1) {
            data.forEachIndexed { index, b ->
                if (b) {
                    str += genreList.genres[index-1]._name + "\n"

                    request += genreList.genres[index-1].id.toString() + ","
                    Log.d("YEAH", str)
                    Log.d("YEAH", request)
                }
            }
        }
        list_genre_tv.text = str
    }


    override fun showGenreList(genreList: GenreList) {
        this.genreList = genreList
        val genreListAdapter = GenreListAdapter(this@MovieFilterFragment, genreList, checkContainer)
        customDialog = GenreListViewDialog(context!!, genreListAdapter)
        customDialog.show()
        customDialog.setCanceledOnTouchOutside(false)
    }

    override fun showResult(movieResponse: MovieResponse?) {
        Log.d("YEAH", "$movieResponse")
    }

}

interface DoneItemClickListener {
    fun onDoneClick(data: BooleanArray)
}