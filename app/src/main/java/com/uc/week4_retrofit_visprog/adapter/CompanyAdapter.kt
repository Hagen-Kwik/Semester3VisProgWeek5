package com.uc.week4_retrofit_visprog.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4_retrofit_visprog.R
import com.uc.week4_retrofit_visprog.helper.Const
import com.uc.week4_retrofit_visprog.view.MovieDetail
import com.uc.week4_retrofit_visprog.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.card_company.view.*
import kotlinx.android.synthetic.main.card_now_playing.view.*



class CompanyAdapter(private val dataSet: List<com.uc.week4_retrofit_visprog.model.ProductionCompany>) :
        RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val img = itemView.img_view_company
        val tvCompany = itemView.tv_company
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_company, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        Glide.with(viewHolder.itemView.context)
            .load(Const.IMG_URL + dataSet.get(position).logo_path)
            .into(viewHolder.img)

        viewHolder.tvCompany.text = dataSet.get(position).name

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
