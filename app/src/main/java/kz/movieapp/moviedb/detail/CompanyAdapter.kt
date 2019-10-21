package kz.movieapp.moviedb.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_company.view.*
import kz.movieapp.moviedb.R
import kz.movieapp.moviedb.models.Company

class CompanyAdapter : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {
    private var companys: List<Company> = ArrayList()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(companys[position])
    }

    override fun getItemCount(): Int {
        return companys.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = (LayoutInflater.from(parent?.context).inflate(R.layout.item_company, parent, false))
        return ViewHolder(root)
    }

    fun addcompany(companys: List<Company>?) {
        if (companys != null) {
            this.companys = companys
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {

        fun bind(company: Company) = with(itemView) {
            Glide.with(this).load(company.getCompanyLogoUrl()).into(company_logo_image)
        }
    }
}