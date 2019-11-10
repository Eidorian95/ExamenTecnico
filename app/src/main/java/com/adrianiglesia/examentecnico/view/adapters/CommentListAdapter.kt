package com.adrianiglesia.examentecnico.view.adapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrianiglesia.examentecnico.R
import com.adrianiglesia.examentecnico.model.Comments
import com.adrianiglesia.examentecnico.model.Hotel
import com.adrianiglesia.examentecnico.model.Review
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.template_comment_item.view.*
import kotlinx.android.synthetic.main.template_hotel_item.view.*

class CommentListAdapter(private val reviews:List<Review>):
    RecyclerView.Adapter<CommentListAdapter.HotelListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.template_comment_item, parent, false)
        return HotelListViewHolder(v)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: HotelListViewHolder, position: Int) {
        holder.bindItems(reviews[position])
    }


    class HotelListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun bindItems(review: Review) {

            itemView.tv_user_name.text = "${review.user.name}, ${review.user.country}"

            itemView.tv_good_comment.text = review.comments.good

            itemView.tv_bad_comment.text = review.comments.bad

        }
    }
}