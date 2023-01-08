package com.cricbuzz.assignment.dipesh.sneakerapp.detail.ui.photos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PhotoAdapter(fragmentManager: FragmentManager, val list: ArrayList<Int>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return PhotoFragment()
    }
}