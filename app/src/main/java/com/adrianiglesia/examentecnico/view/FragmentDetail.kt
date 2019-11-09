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
import com.adrianiglesia.examentecnico.view.adapters.AmentitiesAdapter
import com.adrianiglesia.examentecnico.viewmodel.HotelDetailViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_fragment_detail.*
import kotlin.properties.Delegates


/**
 * A simple [Fragment] subclass.
 */
class FragmentDetail : Fragment() {

    private lateinit var viewModel: HotelDetailViewModel
    private lateinit var id: String
    private var lat by Delegates.notNull<Double>()
    private var lng by Delegates.notNull<Double>()

    companion object {
        private const val ID = "ID"

        fun newInstance(id: String) = FragmentDetail().apply {
            arguments = Bundle(2).apply {
                putString(ID, id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        id = arguments!!.getString("ID")
        viewModel = ViewModelProviders.of(this).get(HotelDetailViewModel::class.java)
        viewModel.getHotelDetail(id).observe(this, Observer {
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

        return inflater.inflate(R.layout.fragment_fragment_detail, container, false)
    }
    
    @SuppressLint("SetTextI18n")
    private fun setUI(hotel: Hotel, price: Price) {
        val imageUrl = hotel.mainPicture.replace("http", "https")
        val uri = Uri.parse(imageUrl)
        Picasso.get().load(uri).into(hotel_image_desc)
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
}
