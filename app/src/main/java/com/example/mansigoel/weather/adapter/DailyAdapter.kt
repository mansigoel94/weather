package com.example.mansigoel.weather.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mansigoel.weather.R
import com.example.mansigoel.weather.Response.DailyDatum
import com.example.mansigoel.weather.Utils
import kotlinx.android.synthetic.main.item_day.view.*

class DailyAdapter(val dailyDatums: List<DailyDatum>?) : RecyclerView.Adapter<DailyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val rootView: View = LayoutInflater.from(parent?.context).
                inflate(R.layout.item_day, parent, false)
        return MyViewHolder(rootView)
    }


    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        val dailyDatum: DailyDatum? = dailyDatums?.get(position)
        holder!!.bindItems(dailyDatum)
    }

    override fun getItemCount(): Int {
        if (dailyDatums != null) {
            return dailyDatums.size
        }
        return 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public fun bindItems(dayDatum: DailyDatum?) {
            if (dayDatum != null) {
                itemView.tv_day.text = Utils.getWeekday(dayDatum.time * 1000)
                itemView.iv_day_icon.setImageResource(Utils.getImage(dayDatum.icon))
                itemView.tv_day_max.text = Math.round(dayDatum.temperatureHigh).toString()
                itemView.tv_day_min.text = Math.round(dayDatum.temperatureLow).toString()
            }
        }
    }
}