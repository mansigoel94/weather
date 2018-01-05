package com.example.mansigoel.weather.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Hourly: Serializable {

    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null
    @SerializedName("data")
    @Expose
    var data: List<HourDatum>? = null

}