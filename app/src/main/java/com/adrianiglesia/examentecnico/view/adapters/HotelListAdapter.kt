package com.adrianiglesia.examentecnico.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.model.Hotel

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
            itemView.setOnClickListener { itemClickListener.onItemClicked(hotel.id) }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(id:String)
    }
}