package com.adrianiglesia.examentecnico.view


import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrianiglesia.examentecnico.R

import com.adrianiglesia.examentecnico.model.Amenity
import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.model.Price
import com.adrianiglesia.examentecnico.service.Network
import com.adrianiglesia.examentecnico.view.adapters.AmentitiesAdapter
import com.adrianiglesia.examentecnico.viewmodel.HotelDetailViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fragment_detail.*
import kotlinx.android.synthetic.main.template_hotel_item.view.*


/**
 * A simple [Fragment] subclass.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FragmentDetail : Fragment() {

    private lateinit var viewModel: HotelDetailViewModel

    companion object {

        fun newInstance() = FragmentDetail().apply {
            arguments = Bundle(0).apply {
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(Network.isConnected(activity)){
            setObeserve()
        }else{
            layout_error.visibility = VISIBLE
            tv_message.text = getString(R.string.error_message_connection)
        }
    }
    
    @SuppressLint("SetTextI18n")
    private fun setUI(hotel: Hotel, price: Price) {

        Glide.with(this)
            .load(hotel.mainPicture)
            .into(hotel_image_desc)

        hotel_name_desc.text = hotel.name
        hotel_location_desc.text = "${hotel.address}, ${hotel.city.name},${hotel.city.country.name}"
        hotel_score_desc.text = hotel.rating.toString()
        hotel_rating_desc.numStars = hotel.stars
        hotel_mask_desc.text = price.currency.mask
        hotel_price_desc.text = price.best.toString()
        hotel_price_currency_descp.text = price.currency.code
        hotel_description_desc.text = hotel.description
        setAmenitiesList(hotel.amenities)
    }

    private fun setVisibilities(visible: Boolean) {
        if (visible) {
            relativeScrollView.visibility = GONE
            progress_detail.visibility = VISIBLE
            layout_price.visibility = GONE
        } else {
            relativeScrollView.visibility = VISIBLE
            progress_detail.visibility = GONE
            layout_price.visibility = VISIBLE
        }
    }

    private fun setAmenitiesList(amenities: List<Amenity>) {
        recycler_amentities_list.layoutManager = LinearLayoutManager(activity)
        recycler_amentities_list.hasFixedSize()
        recycler_amentities_list.adapter = AmentitiesAdapter(amenities)
        recycler_amentities_list.visibility = VISIBLE
    }

    private fun setObeserve(){
        viewModel = activity?.run { ViewModelProviders.of(this).get(HotelDetailViewModel::class.java) }
            ?:throw Exception("Invalid Activity")

        viewModel.getHotelDetail().observe(this, Observer {
            if (it != null) {

                setUI(it.hotel, it.price)

                val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
                mapFragment.getMapAsync{ map ->
                    val location = LatLng(it.hotel.geoLocation.latitude, it.hotel.geoLocation.longitude)
                    map?.addMarker(MarkerOptions().position(location).title("Location"))
                    map?.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15.0f))
                }
            }
        })

        viewModel.getLoading().observe(this, Observer {
            setVisibilities(it)
        })

        viewModel.getMessage().observe(this, Observer {
            if(it != null){
                relativeScrollView.visibility = GONE
                layout_price.visibility = GONE
                layout_error.visibility = VISIBLE
                tv_message.text = it
            }
        })
    }
}
