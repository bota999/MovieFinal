package kz.movieapp.moviedb.movie.moviefilter.widgets

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.filter_genre_list_dialog_layout.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.movie.moviefilter.DoneItemClickListener

class GenreListViewDialog(ctx: Context, internal var adapter: RecyclerView.Adapter<*>) : Dialog(ctx),
    View.OnClickListener{
    var list: BooleanArray = BooleanArray(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.filter_genre_list_dialog_layout)

        val mLayoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = mLayoutManager as RecyclerView.LayoutManager?
        recycler_view.adapter = adapter

        yes.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.yes -> {
                dismiss()
            }
            else -> {

            }
        }
        dismiss()
    }
}


