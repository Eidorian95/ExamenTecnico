package com.adrianiglesia.examentecnico.service

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentActivity

class Network {

    companion object {


        fun isConnected(activity:Activity?):Boolean{

            val conManager  = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val netInfo = conManager.activeNetworkInfo

            return netInfo != null && netInfo.isConnected
        }

        fun isConnected(activity:FragmentActivity?):Boolean{

            val conManager  = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val netInfo = conManager.activeNetworkInfo

            return netInfo != null && netInfo.isConnected
        }

    }

}