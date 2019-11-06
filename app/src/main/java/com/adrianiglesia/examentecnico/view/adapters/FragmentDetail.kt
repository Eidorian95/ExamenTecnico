package com.adrianiglesia.examentecnico.view.adapters


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.viewmodel.HotelDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fragment_detail.*
import kotlinx.android.synthetic.main.template_hotel_item.view.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentDetail(val id: String) : Fragment() {

    lateinit var viewModel: HotelDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HotelDetailViewModel::class.java)
        viewModel.getHotelDetail(id).observe(this, Observer {
            if(it != null){
                setUI(it)
            }
        })

        viewModel.getLoading().observe(this, Observer {
            if(it == true){
                scrollView.visibility = GONE
                progress_detail.visibility = VISIBLE
            }else{
                scrollView.visibility = VISIBLE
                progress_detail.visibility = GONE
            }

        })

        return inflater.inflate(R.layout.fragment_fragment_detail, container, false)
    }


    private fun setUI(hotel: Hotel){
        val imageUrl = hotel.mainPicture.replace("http","https")
        val uri= Uri.parse(imageUrl)
        Picasso.get().load(uri).into(hotel_image_desc)
        hotel_name_desc.text = hotel.name
        hotel_location_desc.text = "${hotel.address}, ${hotel.city.name},${hotel.city.country.name}"
        hotel_score_desc.text = hotel.rating.toString()
        hotel_rating_desc.numStars = hotel.stars
        hotel_description_desc.text = hotel.description

    }

}
