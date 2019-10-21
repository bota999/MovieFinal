package kz.movieapp.moviedb.movie.moviefilter.widgets

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.filter_genre_item.view.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.response.GenreList
import kz.movieapp.moviedb.movie.moviefilter.DoneItemClickListener

class GenreListAdapter(
    internal var doneItemClickListener: DoneItemClickListener,
    private val genreList: GenreList,
    internal var checkContainer: BooleanArray
) : RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, i: Int): GenreViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.filter_genre_item, parent, false)
        return GenreViewHolder(v)
    }

    override fun onBindViewHolder(genreViewHolder: GenreViewHolder, i: Int) {
        genreViewHolder.checkBox.isChecked = checkContainer[i+1]
        genreViewHolder.checkBox.setOnClickListener {
            checkContainer[i+1] = !checkContainer[i+1]
            doneItemClickListener.onDoneClick(checkContainer)
        }
        genreViewHolder.mTextView.text = genreList.genres[i]._name
    }

    override fun getItemCount(): Int {
        return genreList.genres.size
    }


    inner class GenreViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var mTextView: TextView
        var checkBox: CheckBox

        init {
            mTextView = v.textView
            checkBox = v.filter_genre_list_item_cb
        }

    }
}