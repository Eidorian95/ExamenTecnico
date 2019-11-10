package com.adrianiglesia.examentecnico.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.view.adapters.PageAdapterDetail
import com.adrianiglesia.examentecnico.viewmodel.HotelDetailViewModel
import kotlinx.android.synthetic.main.activity_hotel_detail.*

class HotelDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: HotelDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)
        val id = intent.getStringExtra("ID")

        viewModel = ViewModelProviders.of(this).get(HotelDetailViewModel::class.java)
        viewModel.loadDetails(id)


        val fragmentAdapter = PageAdapterDetail(supportFragmentManager,id)
        page_viewer.adapter = fragmentAdapter
        tabs.setupWithViewPager(page_viewer)
    }
}
