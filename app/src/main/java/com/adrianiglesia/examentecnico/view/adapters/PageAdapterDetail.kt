package com.adrianiglesia.examentecnico.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.adrianiglesia.examentecnico.view.FragmentDetail
import com.adrianiglesia.examentecnico.view.FragmentReviews

class PageAdapterDetail(fm:FragmentManager, val id:String): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentDetail.newInstance()
            }
            else -> FragmentReviews.newInstance(id)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Descripción"
            else -> "Comentarios"
        }
    }
}