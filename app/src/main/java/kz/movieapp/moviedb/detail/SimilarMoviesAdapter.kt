package kz.movieapp.moviedb.detail

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie_small.view.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Movie

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder>() {
    private var movies: List<Movie> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent?.context).inflate(R.layout.item_movie_small, parent, false))
        return ViewHolder(root)
    }

    fun addMovies(movies: List<Movie>?) {
        if (movies != null) {
            this.movies = movies
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    val intent = Intent(root.context, DetailActivity::class.java)
                    intent.putExtra("id", movies[adapterPosition].id)
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (root.context as Activity),
                        itemView,
                        root.context.getString(R.string.film_transition)
                    )
                    root.context?.let {
                            it1 ->
                        ContextCompat.startActivity(it1, intent, options.toBundle())
                    }
                }
            }
        }

        fun bind(movie: Movie) = with(itemView) {
            //            company_name?.text = company.name
            Glide.with(this).load(movie.getPosterUrl()).into(poster)
            title.text = movie.title
        }
    }
}