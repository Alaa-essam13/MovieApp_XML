package com.example.movieapp_xml.ui.adapters

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.movieapp_xml.ui.Domain.SliderItem
import com.example.movieapp_xml.R

class SlideAdapter(
    private val SliderItems: List<SliderItem>,
    private val viewPager: ViewPager2
) : RecyclerView.Adapter<SlideAdapter.SliderViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item_container, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SliderViewHolder,
        position: Int
    ) {
    holder.bind(SliderItems[position])
    }

    override fun getItemCount(): Int = SliderItems.size

    // ViewHolder class
    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)
        fun bind(sliderItem: SliderItem){
            val request = RequestOptions()
                .transforms(CenterCrop(), RoundedCorners(60))
            Glide.with(itemView.context)
                .load(sliderItem.image)
                .apply(request)
                .into(imageView)
        }
    }



}