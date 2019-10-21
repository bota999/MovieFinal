package kz.movieapp.moviedb.search_history

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.item_movie_simple_view.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.detail.DetailActivity
import kz.movieapp.moviedb.models.Movie


class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {
    private var movies: List<Movie> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent?.context).inflate(R.layout.item_movie_simple_view, parent, false))
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

                    var tempMovies: MutableMap<String,Movie> = mutableMapOf()
                    GlobalScope.launch {
                        val c1 = async {
                            tempMovies = getMoviesFromShared(itemView.context)
                            tempMovies.put(movies[adapterPosition].id,movies[adapterPosition])
                        }
                        c1.await()
                        saveMoviesToShared(itemView.context, tempMovies)
                    }
//                    notifyDataSetChanged()
                    val intent = Intent(root.context, DetailActivity::class.java)
                    intent.putExtra("id", movies[adapterPosition].id)
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bind(movie: Movie) = with(itemView) {
            search_history_title_tv.text = movie.title
        }

        fun saveMoviesToShared(context: Context, movies: MutableMap<String,Movie>?) {
            val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
            val prefsEditor = appSharedPrefs.edit()
            val gson = Gson()
            val json = gson.toJson(movies)
            prefsEditor.putString("historyMoviesMp", json)
            prefsEditor.apply()
        }

        fun getMoviesFromShared(context: Context):  MutableMap<String,Movie> {
            val appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
            val gson = Gson()
            val json = appSharedPrefs.getString("historyMoviesMp", null)
            val turnsType = object : TypeToken<MutableMap<String,Movie>>() {}.type
            val movieList = gson.fromJson<MutableMap<String,Movie>>(json, turnsType)
            val emptyList = mutableMapOf<String,Movie>()
            if (movieList == null)
                return emptyList
            else
                return movieList
        }
    }
}