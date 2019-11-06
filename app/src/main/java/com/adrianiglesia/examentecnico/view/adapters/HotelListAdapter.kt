package com.adrianiglesia.examentecnico.view.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.model.Hotel
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.template_hotel_item.view.*

class HotelListAdapter(private val hoteles:List<Hotel>, private val itemClickListener: OnItemClickListener):
    RecyclerView.Adapter<HotelListAdapter.HotelListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_hotel_item,parent,false)
        return HotelListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return hoteles.size
    }

    override fun onBindViewHolder(holder: HotelListViewHolder, position: Int) {
        holder.bindItems(hoteles[position], itemClickListener)
    }


    class HotelListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindItems(hotel: Hotel, itemClickListener: OnItemClickListener) {

            val imageUrl = hotel.mainPicture.replace("http","https")
            val uri=Uri.parse(imageUrl)
            Picasso.get().load(uri).into(itemView.hotel_image)

            itemView.hotel_name.text = hotel.name
            itemView.hotel_location.text = hotel.address
            itemView.hotel_score.text = hotel.rating.toString()
            itemView.hotel_rating.setIsIndicator(true)
            itemView.hotel_rating.rating = hotel.stars.toFloat()
            itemView.hotel_price.text = hotel.price.best.toString()

            itemView.setOnClickListener { itemClickListener.onItemClicked(hotel.id) }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(id:String)
    }
}