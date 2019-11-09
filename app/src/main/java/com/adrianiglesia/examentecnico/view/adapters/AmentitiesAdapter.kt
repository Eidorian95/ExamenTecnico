package com.adrianiglesia.examentecnico.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.model.Amenity
import kotlinx.android.synthetic.main.template_amentities_list.view.*

class AmentitiesAdapter(private val amentities:List<Amenity>):RecyclerView.Adapter<AmentitiesAdapter.AmenitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenitiesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_amentities_list,parent,false)
        return AmenitiesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return amentities.size
    }

    override fun onBindViewHolder(holder: AmenitiesViewHolder, position: Int) {
        holder.bindItems(amentities[position])
    }


    class AmenitiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val PISCN:String = "PISCN"
        private val BREAKFST:String = "BREAKFST"
        private val WIFI:String = "WIFI"
        private val PARKING:String = "PARKING"

        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindItems(amenity: Amenity) {

            when(amenity.id){

                PISCN -> itemView.icon_amenity.setImageResource(R.drawable.ic_pool)
                BREAKFST -> itemView.icon_amenity.setImageResource(R.drawable.ic_free_breakfast)
                WIFI -> itemView.icon_amenity.setImageResource(R.drawable.ic_wifi)
                PARKING -> itemView.icon_amenity.setImageResource(R.drawable.ic_local_parking)
                else -> itemView.icon_amenity.visibility = GONE
            }

            itemView.desc_amentitie.text = amenity.description

        }
    }
}