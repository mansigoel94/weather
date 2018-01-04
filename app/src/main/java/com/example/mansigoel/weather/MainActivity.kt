package com.example.mansigoel.weather

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.mansigoel.weather.Response.*
import com.example.mansigoel.weather.adapter.DailyAdapter
import com.example.mansigoel.weather.adapter.HourAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.extra_card.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var root: Root? = null;
    private var currently: Currently? = null;
    private var hourly: Hourly? = null;
    private var daily: Daily? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASEURL)
                .build();

        val apiInterface = retrofit.create(WeatherApiInterface::class.java);

        Log.d("Mansi", "latitude: " + Utils.getLatitude(this));
        Log.d("Mansi", "longitude: " + Utils.getLongitude(this));

        //TODO Fetch location data from phone, currently its hardcoded
        //TODO Add view pager to show upto 3 weather reports
        val call = apiInterface.fetchData(
                18.5204, 73.8567, UNIT);
        call.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>?, response: Response<Root>?) {
                Log.d("Mansi", "Response")
                if (response != null) {
                    if (response.isSuccessful) {
                        pb_loader.visibility = View.GONE
                        cl_container.visibility = View.VISIBLE;
                        root = response?.body();
                        //populating place hardcoded coz it is not present in Json
                        tv_place.text = "PUNE";

                        //populating weather summary, average temp and today's day name from currently object
                        currently = root?.currently;
                        tv_weather_data.text = currently!!.summary;
                        tv_temperature_average.text = Math.round(currently!!.temperature).toString();
                        tv_weekday.text = Utils.getWeekday(currently!!.time * 1000)

                        //populating minimum and maximum temperature for today
                        //used data[1] coz data[0] shows results of yesterday
                        daily = root?.daily;
                        tv_temp_max.text = Math.round(daily!!.data!!.get(1)?.temperatureHigh).toString();
                        tv_temp_min.text = Math.round(daily!!.data!!.get(1)?.temperatureLow!!).toString();

                        //populating hour recycler view
                        hourly = root?.hourly
                        val layoutManagerHour: LinearLayoutManager = LinearLayoutManager(
                                baseContext, LinearLayoutManager.HORIZONTAL, false)
                        val adapterHour = HourAdapter(hourly?.data)
                        rv_hour_data.layoutManager = layoutManagerHour
                        rv_hour_data.adapter = adapterHour

                        //populating daily recycler view
                        daily = root?.daily
                        val layoutManagerDaily: LinearLayoutManager = LinearLayoutManager(
                                baseContext, LinearLayoutManager.VERTICAL, false)
                        val adapterDaily = DailyAdapter(daily?.data)
                        rv_week_data.layoutManager = layoutManagerDaily
                        rv_week_data.adapter = adapterDaily

                        //populating card data
                        val dailyDatum = daily?.data?.get(1)
                        if (dailyDatum != null) {
                            tv_sunrise.text = Utils.getTime((dailyDatum.sunriseTime) * 1000)
                            tv_sunset.text = Utils.getTime((dailyDatum.sunsetTime) * 1000)

                            tv_humidity.text = Math.round(dailyDatum.humidity * 100).toString()
                            //TODO Adding wind direction is remaining and
                            // TODO wind speed is also coming in mi/hr-convert it to km/hr
                            tv_wind.text = dailyDatum?.windSpeed.toString();
                            tv_precipitation.text = dailyDatum.precipIntensity.toString() + " cm"
                            tv_pressure.text = Math.round(dailyDatum.pressure).toString() + " hPa"
                            tv_uv_index.text = dailyDatum.uvIndex.toString()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Root>?, t: Throwable?) {
                Log.d("Mansi", "Failed")
            }
        })
    }
}
