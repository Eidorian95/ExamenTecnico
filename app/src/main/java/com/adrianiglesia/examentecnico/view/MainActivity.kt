package com.adrianiglesia.examentecnico.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.service.Network
import com.adrianiglesia.examentecnico.view.adapters.HotelListAdapter
import com.adrianiglesia.examentecnico.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HotelListAdapter.OnItemClickListener {


    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Network.isConnected(this)){
            setObserve()
        }else{
            layout_error_main.visibility = VISIBLE
            tv_message_main.text = getString(R.string.error_message_connection)
        }


    }

    override fun onItemClicked(id: String) {
        val intent = Intent(this, HotelDetailActivity::class.java)
        intent.putExtra("ID",id)
        startActivity(intent)
    }

    private fun setRecyclerView(hoteles:List<Hotel>){
        recycler_hoteles.layoutManager = LinearLayoutManager(this)
        recycler_hoteles.hasFixedSize()
        recycler_hoteles.adapter = HotelListAdapter(hoteles,this)
        recycler_hoteles.visibility = VISIBLE
    }

    private fun setObserve(){
        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainViewModel.getAllHotels()?.observe(this, Observer {
            if(it != null){
                setRecyclerView(it)
            }
        })

        mainViewModel.getMessage().observe(this, Observer {
            Log.d("MESSAGE",it)
        })

        mainViewModel.getLoading().observe(this, Observer {
            if(it == true){
                recycler_hoteles.visibility = GONE
                progress_main.visibility = VISIBLE
            }else{
                recycler_hoteles.visibility = VISIBLE
                progress_main.visibility = GONE
            }
        })
    }
}
