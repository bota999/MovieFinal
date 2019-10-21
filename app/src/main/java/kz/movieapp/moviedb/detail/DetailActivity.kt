package kz.movieapp.moviedb.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kz.movieapp.moviedb.App
import kz.movieapp.moviedb.BuildConfig
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.MovieDetail
import kz.movieapp.moviedb.models.Videos
import kz.movieapp.moviedb.models.response.MovieResponse
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailView {
    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        (applicationContext as App).createDetailComponent().inject(this)

        val id = intent.getStringExtra("id")
        initLayout()
        presenter.setView(this, id)

    }

    override fun showMovieDetails(movies: MovieDetail?) {
        val rateAvg = movies?.voteAverage?.div(2)
        loading.visibility = View.GONE

        overview?.text = movies?.overview
        rate?.text = movies?.voteAverage.toString()
        vote_count?.text = movies?.voteCount.toString()
        release_date?.text = movies?.releaseDate
        runtime?.text = movies?.runtime.toString()
        if (rateAvg != null) {
            rating_bar.rating = rateAvg.toFloat()
        }
        loadImage(movies?.getPosterUrl(), image_detail)

        (company_list.adapter as CompanyAdapter).addcompany(movies?.companies)

    }

    private fun loadImage(posterUrl: String?, image_detail: ImageView) {
        runBlocking<Unit> {
            launch {
                val imageFullLoading = async {
                    Glide.with(applicationContext).load(posterUrl).into(image_detail)
                }
                imageFullLoading.await()
            }
        }
    }

    override fun getVideos(videos: List<Videos>?) {
        val key = videos?.get(0)?.key
        youtube_video.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.YOUTUBE+key))
            startActivity(intent)
        }
    }

    override fun showSimilarMovies(movies: MovieResponse?) {
        (similar_movies_list.adapter as SimilarMoviesAdapter).addMovies(movies?.movies)
    }

    private fun initLayout() {
        company_list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        company_list.layoutManager = layoutManager
        company_list.setHasFixedSize(true)
        company_list.adapter = CompanyAdapter()

        similar_movies_list.setHasFixedSize(true)
        val layoutManagerSimilarMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        similar_movies_list.layoutManager = layoutManagerSimilarMovies
        similar_movies_list.setHasFixedSize(true)
        similar_movies_list.adapter = SimilarMoviesAdapter()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}