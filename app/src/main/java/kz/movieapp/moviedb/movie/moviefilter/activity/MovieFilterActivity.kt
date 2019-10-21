package kz.movieapp.moviedb.movie.moviefilter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_movie_filter.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.movie.nowplaying.NowPlaying

class MovieFilterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_filter)
        val from = intent.getStringExtra("fromKey")
        val to = intent.getStringExtra("toKey")
        val request = intent.getStringExtra("requestKey")
        setSupportActionBar(toolbar_filter)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadMovieFilterFragment(savedInstanceState, from, to, request)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadMovieFilterFragment(
        savedInstanceState: Bundle?,
        from: String,
        to: String,
        request: String
    ) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container_filter_movie, NowPlaying(from, to, request), NowPlaying::class.simpleName)
                .commit()
        }
    }
}
