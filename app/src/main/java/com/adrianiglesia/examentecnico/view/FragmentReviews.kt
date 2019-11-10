package com.adrianiglesia.examentecnico.view


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
import com.adrianiglesia.examentecnico.model.Review
import com.adrianiglesia.examentecnico.service.Network
import com.adrianiglesia.examentecnico.view.adapters.CommentListAdapter
import com.adrianiglesia.examentecnico.viewmodel.HotelDetailViewModel
import kotlinx.android.synthetic.main.fragment_fragment_reviews.*

/**
 * A simple [Fragment] subclass.
 */
class FragmentReviews : Fragment() {

    private lateinit var viewModel: HotelDetailViewModel
    lateinit var id: String

    companion object {
        private const val ID = "ID"
        fun newInstance(id: String) = FragmentReviews().apply {
            arguments = Bundle(1).apply {
                putString(ID, id)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_fragment_reviews, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(Network.isConnected(activity)){
            setObserve()
        }else{
            layout_no_reviews.visibility = GONE
            layout_error.visibility = VISIBLE
            tv_message.text = "No se ha detectado conexion, verifique y vuelva a intentar"
        }
    }
    private fun setReviewsList(reviews: List<Review>) {
        recycler_comment_list.layoutManager = LinearLayoutManager(activity)
        recycler_comment_list.hasFixedSize()
        recycler_comment_list.adapter = CommentListAdapter(reviews)
        recycler_comment_list.visibility = VISIBLE
    }

    private fun setObserve(){
        viewModel = activity?.run { ViewModelProviders.of(this).get(HotelDetailViewModel::class.java) }
            ?: throw Exception("Invalid Activity")

        viewModel.getReviews().observe(this, Observer {
            if(it != null){
                layout_no_reviews.visibility = GONE
                setReviewsList(it)
            }
        })
    }

}
