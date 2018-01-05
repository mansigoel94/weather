package com.example.mansigoel.weather.adapter

import android.content.Context
import android.provider.DocumentsContract
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.mansigoel.weather.Response.Root
import com.example.mansigoel.weather.fragment.MainFragment

class MainPagerAdapter(fragmentManager: FragmentManager, val root: Root?) :
        FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {
        return MainFragment.newInstance(root)
    }

    override fun getCount(): Int {
        return 3
    }
}