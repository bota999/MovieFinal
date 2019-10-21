package kz.movieapp.moviedb.movie.movies


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kz.movieapp.moviedb.App

import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Movie
import kz.movieapp.moviedb.movie.movies.adapters.ViewPagerAdapter
import kz.movieapp.moviedb.movie.popularmovies.PopularMovies
import kz.movieapp.moviedb.movie.upcoming.UpcomingFragment

import javax.inject.Inject

class MovieFragment : Fragment(), MoviePartView {
    override fun getTabs() {
        movie_part_view_pager.adapter = ViewPagerAdapter(childFragmentManager)
        movie_part_tab_layout.setupWithViewPager(movie_part_view_pager)
    }

    @Inject
    lateinit var presenter: MoviePartPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (context?.applicationContext as App).createMainComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
    }

}
