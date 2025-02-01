package com.example.movieapp_xml.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp_xml.R
import com.example.movieapp_xml.data.remote.Data
import com.example.movieapp_xml.ui.activities.DetailActivity

class FilmListAdapter(
    private val Data: List<Data>
) : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slideitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(Data[position], position)
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id",Data[position].title)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = Data.size

    // ViewHolder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitle: TextView = itemView.findViewById(R.id.titleTxt)
        private val movieGenre: ImageView = itemView.findViewById(R.id.imgContainer)
        fun bind(model: Data,position: Int) {
            movieTitle.text = model.title
            val request = RequestOptions()
                .transforms(CenterCrop(), RoundedCorners(60))
            Glide.with(itemView.context)
                .load(model.poster)
                .apply(request)
                .into(movieGenre)
        }
    }


}