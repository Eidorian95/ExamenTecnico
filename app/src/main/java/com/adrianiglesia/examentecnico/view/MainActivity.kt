package com.adrianiglesia.examentecnico.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainViewModel.getAllHotels()?.observe(this, Observer {
            Log.d("HOTELES", it?.size.toString())
        })

        mainViewModel.getMessage().observe(this, Observer {
            Log.d("MESSAGE",it)
        })

    }
}
