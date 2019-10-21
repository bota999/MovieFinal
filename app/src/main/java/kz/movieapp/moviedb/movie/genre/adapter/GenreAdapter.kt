package kz.movieapp.moviedb.movie.genre.adapter


import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_company.view.*
import kotlinx.android.synthetic.main.item_genre_recycler.view.*
import kz.movieapp.moviedb.NavActivity
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Genre
import kz.movieapp.moviedb.movie.genre.activity.GenreActivity


class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    private var genres: List<Genre> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent?.context).inflate(R.layout.item_genre_recycler, parent, false))
        return ViewHolder(root)
    }

    fun addGenre(genre: List<Genre>?) {
        if (genre != null) {
            this.genres = genre
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(genre: Genre) = with(itemView) {
            genre_item_rv.text = genre._name
            genre_item_rv.setOnClickListener {
                val activity = Intent(itemView.context, GenreActivity::class.java)
                activity.putExtra("idGenre", genres[adapterPosition].id.toString())
                activity.putExtra("idGenreName", genres[adapterPosition]._name)
                itemView.context.startActivity(activity,ActivityOptions.makeSceneTransitionAnimation((context as Activity)).toBundle())
            }
        }
    }
}