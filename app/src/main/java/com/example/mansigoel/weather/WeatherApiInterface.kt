package com.example.mansigoel.weather

import com.example.mansigoel.weather.Response.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiInterface {
    //apikey is 1 path and 2nd path will be location argument
    @GET("$APIKEY/{$LATITUDE_KEY},{$LONGITUDE_KEY}")
    fun fetchData(@Path(LATITUDE_KEY) lat: Double, @Path(LONGITUDE_KEY) long: Double,
                  @Query(QUERY_UNIT) unit: String)
            : Call<Root>
}