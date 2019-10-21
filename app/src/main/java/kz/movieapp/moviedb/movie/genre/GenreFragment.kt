package kz.movieapp.moviedb.movie.genre


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.app_bar_nav.*
import kotlinx.android.synthetic.main.fragment_genre.*
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.NavActivity

import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.movie.genre.adapter.GenreAdapter
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class GenreFragment : Fragment(), GenrePartView {
    override fun showGenres(genreList: GenreList) {
        (genre_list.adapter as GenreAdapter).addGenre(genreList.genres)
    }

    @Inject
    lateinit var presenter: GenrePartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (context?.applicationContext as App).createMainComponent().inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)

        initLayout()
    }


    private fun initLayout() {
        genre_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        genre_list.layoutManager = layoutManager
        genre_list.setHasFixedSize(true)
        genre_list.adapter = GenreAdapter()
    }

}
