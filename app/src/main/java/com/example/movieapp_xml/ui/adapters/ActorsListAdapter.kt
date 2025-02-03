package com.example.movieapp_xml.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp_xml.R

class ActorsListAdapter(
    private val imgs: List<String>
) : RecyclerView.Adapter<ActorsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_actors, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(imgs[position])
    }

    override fun getItemCount(): Int = imgs.size

    // ViewHolder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actorimg: ImageView = itemView.findViewById(R.id.itemImage)
        fun bind(img:String){
            val request = RequestOptions()
                .transforms(CenterCrop(), RoundedCorners(60))
            Glide.with(itemView.context)
                .load(img)
                .apply(request)
                .into(actorimg)
        }
    }


}