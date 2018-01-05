package com.example.mansigoel.weather.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mansigoel.weather.R
import com.example.mansigoel.weather.R.id.*
import com.example.mansigoel.weather.Response.Currently
import com.example.mansigoel.weather.Response.Daily
import com.example.mansigoel.weather.Response.Hourly
import com.example.mansigoel.weather.Response.Root
import com.example.mansigoel.weather.Utils
import com.example.mansigoel.weather.adapter.DailyAdapter
import com.example.mansigoel.weather.adapter.HourAdapter
import kotlinx.android.synthetic.main.view_main.*
import kotlinx.android.synthetic.main.extra_card.*
import kotlinx.android.synthetic.main.extra_card.view.*
import kotlinx.android.synthetic.main.view_main.view.*


class MainFragment : Fragment() {

    private var root: Root? = null;
    private var currently: Currently? = null;
    private var hourly: Hourly? = null;
    private var daily: Daily? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.view_main, container, false)
        if (arguments != null) {
            root = arguments.getSerializable("response") as Root?
            updateUI(view);
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance(response: Root?): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putSerializable("response", response)
            fragment.arguments = args
            return fragment
        }
    }

    private fun updateUI(view: View) {
        view.cl_container.visibility = View.VISIBLE;
        //populating place hardcoded coz it is not present in Json
        //TODO Reverse logic to get city name from latitude longitude
        view.tv_place.text = "PUNE";

        //populating weather summary, average temp and today's day name from currently object
        currently = root?.currently;
        view.tv_weather_data.text = currently!!.summary;
        view.tv_temperature_average.text = Math.round(currently!!.temperature).toString();
        view.tv_weekday.text = Utils.getWeekday(currently!!.time * 1000)

        //populating minimum and maximum temperature for today
        //used data[1] coz data[0] shows results of yesterday
        daily = root?.daily;
        view.tv_temp_max.text = Math.round(daily!!.data!!.get(1)?.temperatureHigh).toString();
        view.tv_temp_min.text = Math.round(daily!!.data!!.get(1)?.temperatureLow!!).toString();

        //populating hour recycler view
        hourly = root?.hourly
        val layoutManagerHour: LinearLayoutManager = LinearLayoutManager(
                activity.baseContext, LinearLayoutManager.HORIZONTAL, false)
        val adapterHour = HourAdapter(hourly?.data)
        view.rv_hour_data.layoutManager = layoutManagerHour
        view.rv_hour_data.adapter = adapterHour

        //populating daily recycler view
        daily = root?.daily
        val layoutManagerDaily: LinearLayoutManager = LinearLayoutManager(
                activity.baseContext, LinearLayoutManager.VERTICAL, false)
        val adapterDaily = DailyAdapter(daily?.data)
        view.rv_week_data.layoutManager = layoutManagerDaily
        view.rv_week_data.adapter = adapterDaily

        //populating card data
        val dailyDatum = daily?.data?.get(1)
        if (dailyDatum != null) {
            view.tv_sunrise.text = Utils.getTime((dailyDatum.sunriseTime) * 1000)
            view.tv_sunset.text = Utils.getTime((dailyDatum.sunsetTime) * 1000)

            view.tv_humidity.text = Math.round(dailyDatum.humidity * 100).toString()

            //wind speed and direction
            view.tv_wind.text = dailyDatum?.windSpeed.toString();
            view.tv_precipitation.text = dailyDatum.precipIntensity.toString() + " cm"
            view.tv_pressure.text = Math.round(dailyDatum.pressure).toString() + " hPa"
            view.tv_uv_index.text = dailyDatum.uvIndex.toString()
        }
    }
}