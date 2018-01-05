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
import com.example.mansigoel.weather.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.extra_card.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var fragmentPagerAdapter: MainPagerAdapter? = null
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
        val call = apiInterface.fetchData(
                18.5204, 73.8567, UNIT);
        call.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>?, response: Response<Root>?) {
                Log.d("Mansi", "Response")
                if (response != null) {
                    if (response.isSuccessful) {
                        pb_loader.visibility = View.GONE
                        fragmentPagerAdapter = MainPagerAdapter(
                                supportFragmentManager, response?.body())
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
}
