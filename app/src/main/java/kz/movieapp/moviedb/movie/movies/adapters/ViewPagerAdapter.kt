package kz.movieapp.moviedb.movie.movies.adapters

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.coroutines.*
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.movie.popularmovies.PopularMovies
import kz.movieapp.moviedb.movie.upcoming.UpcomingFragment
import javax.inject.Inject

class ViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager) {
    var listFragment = mutableListOf<Fragment>()

    init {
        runBlocking<Unit> {
            launch {
                val fp = async {
                    listFragment.add(PopularMovies())
                }
                val sp = async {
                    listFragment.add(UpcomingFragment())
                }
                fp.await()
                sp.await()
            }
        }
    }

    override fun getItem(position: Int): Fragment {
        return listFragment[position]
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0) {
            return "Популярные"
        } else {
            return "Скоро на экранах"
        }
    }
}