package kz.movieapp.moviedb.movie.genre.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_genre.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.movie.genrefilter.GenreFilter

class GenreActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)
        val id = intent.getStringExtra("idGenre")
        val name = intent.getStringExtra("idGenreName")
        setSupportActionBar(toolbar_genre)
        supportActionBar?.title = name
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadGenreFilterFragment(savedInstanceState, id)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadGenreFilterFragment(savedInstanceState: Bundle?, id: String) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container_genre, GenreFilter(id), GenreFilter::class.simpleName)
                .commit()
        }
    }

}
