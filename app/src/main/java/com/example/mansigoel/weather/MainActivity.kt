package com.example.mansigoel.weather

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.example.mansigoel.weather.Response.Root
import com.example.mansigoel.weather.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class MainActivity : AppCompatActivity() {
    var fragmentPagerAdapter: MainPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.my_animation)
        iv_splash.animation = animation

        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASEURL)
                .build();

        val apiInterface = retrofit.create(WeatherApiInterface::class.java);

        val location = fetchLocationAfterPermission()

        Log.d("Mansi", "latitude: " + location?.latitude);
        Log.d("Mansi", "longitude: " + location?.longitude);

        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses = geocoder.getFromLocation(location!!.latitude, location!!.longitude, 1)
        val city = addresses[0].locality
        Log.d("Mansi", "city : " + city);


        val call = apiInterface.fetchData(
                location!!.latitude, location!!.longitude, UNIT);
        call.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>?, response: Response<Root>?) {
                Log.d("Mansi", "Response")
                if (response != null) {
                    if (response.isSuccessful) {
                        iv_splash.clearAnimation()
                        iv_splash.visibility = View.GONE
                        fragmentPagerAdapter = MainPagerAdapter(
                                supportFragmentManager, response?.body(),city)
                        //view pager to show upto 3 weather reports
                        viewPager.adapter = fragmentPagerAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Root>?, t: Throwable?) {
                Log.d("Mansi", "Failed")
            }
        })
    }

    @SuppressLint("MissingPermission")
    fun fetchLocationAfterPermission(): Location? {
        val mLocationManager = applicationContext.getSystemService(LOCATION_SERVICE)
                as LocationManager
        val providers = mLocationManager.getProviders(true);
        var bestLocation: Location? = null;
        for (provider in providers) {
            val l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }
}
