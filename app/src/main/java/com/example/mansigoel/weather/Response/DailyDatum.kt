package com.example.mansigoel.weather.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DailyDatum {

    @SerializedName("time")
    @Expose
    var time: Long = 0
    @SerializedName("summary")
    @Expose
    var summary: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null
    @SerializedName("sunriseTime")
    @Expose
    var sunriseTime: Long = 0
    @SerializedName("sunsetTime")
    @Expose
    var sunsetTime: Long = 0
    @SerializedName("moonPhase")
    @Expose
    var moonPhase: Double = 0.toDouble()
    @SerializedName("precipIntensity")
    @Expose
    var precipIntensity: Double = 0.0
    @SerializedName("temperatureHigh")
    @Expose
    var temperatureHigh: Double = 0.toDouble()
    @SerializedName("temperatureHighTime")
    @Expose
    var temperatureHighTime: Int = 0
    @SerializedName("temperatureLow")
    @Expose
    var temperatureLow: Double = 0.toDouble()
    @SerializedName("temperatureLowTime")
    @Expose
    var temperatureLowTime: Int = 0

    @SerializedName("humidity")
    @Expose
    var humidity: Double = 0.toDouble()
    @SerializedName("pressure")
    @Expose
    var pressure: Double = 0.toDouble()
    @SerializedName("windSpeed")
    @Expose
    var windSpeed: Double = 0.toDouble()
    @SerializedName("windGust")
    @Expose
    var windGust: Double = 0.toDouble()
    @SerializedName("windGustTime")
    @Expose
    var windGustTime: Int = 0
    @SerializedName("windBearing")
    @Expose
    var windBearing: Int = 0
    @SerializedName("uvIndex")
    @Expose
    var uvIndex: Int=0
}