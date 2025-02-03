package com.example.movieapp_xml.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp_xml.data.remote.GenresItem
import com.example.movieapp_xml.R

class CategoryEachFilmListAdapter(
    private val s: List<String>
) : RecyclerView.Adapter<CategoryEachFilmListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(s[position])
    }

    override fun getItemCount(): Int = s.size

    // ViewHolder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName: TextView = itemView.findViewById(R.id.categorytx)
        fun bind(genresItem: String){
            categoryName.text = genresItem
        }
    }


}