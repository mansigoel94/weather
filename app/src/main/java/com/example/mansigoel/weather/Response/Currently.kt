package com.example.mansigoel.weather.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Currently: Serializable {

    @SerializedName("time")
    @Expose
    var time: Long = 0
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null

    @SerializedName("precipIntensity")
    @Expose
    var precipIntensity: Double = 0.0

    /*@SerializedName("precipProbability")
    @Expose
    var precipProbability: Int = 0*/

    @SerializedName("temperature")
    @Expose
    var temperature: Double = 0.toDouble()
    @SerializedName("apparentTemperature")
    @Expose
    var apparentTemperature: Double = 0.toDouble()

    /*@SerializedName("dewPoint")
    @Expose
    var dewPoint: Double = 0.toDouble()*/

    @SerializedName("humidity")
    @Expose
    var humidity: Double = 0.toDouble()
    @SerializedName("pressure")
    @Expose
    var pressure: Double = 0.toDouble()
    @SerializedName("windSpeed")
    @Expose
    var windSpeed: Double = 0.toDouble()

    /*@SerializedName("windGust")
    @Expose
    var windGust: Double = 0.toDouble()*/

    @SerializedName("windBearing")
    @Expose
    var windBearing: Int = 0

    /*@SerializedName("cloudCover")
    @Expose
    var cloudCover: Int = 0*/

    @SerializedName("uvIndex")
    @Expose
    var uvIndex: Int = 0

    /* @SerializedName("ozone")
     @Expose
     var ozone: Double = 0.toDouble()*/
}