package com.example.mansigoel.weather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mansigoel.weather.R
import com.example.mansigoel.weather.Response.HourDatum
import com.example.mansigoel.weather.Utils
import com.example.mansigoel.weather.adapter.HourAdapter.MyViewHolder
import kotlinx.android.synthetic.main.item_hour.view.*

class HourAdapter(val hours: List<HourDatum>?) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val root: View = LayoutInflater.from(parent?.context).inflate(
                R.layout.item_hour, parent, false);
        return MyViewHolder(root);
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val hourDatum: HourDatum? = hours?.get(position)
        holder!!.bindItems(hourDatum);
    }

    override fun getItemCount(): Int {
        if (hours != null) {
            return hours.size
        }
        return 0;
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(hour: HourDatum?) {
            if (hour != null) {
                itemView.tv_hour.text = Utils.getHour(hour.time * 1000)
                itemView.tv_hour_temp.text = Math.round(hour.temperature).toString();
                itemView.iv_hour_icon.setImageResource(Utils.getImage(hour.icon))
            }
        }
    }
}