package com.example.mansigoel.weather.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.mansigoel.weather.Response.Root
import com.example.mansigoel.weather.fragment.MainFragment

class MainPagerAdapter(fragmentManager: FragmentManager, val root: Root?, val city: String) :
        FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {
        return MainFragment.newInstance(root,city)
    }

    override fun getCount(): Int {
        return 3
    }
}