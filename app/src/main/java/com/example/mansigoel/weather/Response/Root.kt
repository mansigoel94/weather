package com.example.mansigoel.weather.Response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Root: Serializable {

    @SerializedName("latitude")
    @Expose
    var latitude: Double = 0.toDouble()
    @SerializedName("longitude")
    @Expose
    var longitude: Double = 0.toDouble()
    @SerializedName("timezone")
    @Expose
    var timezone: String? = null
    @SerializedName("currently")
    @Expose
    var currently: Currently? = null
    @SerializedName("hourly")
    @Expose
    var hourly: Hourly? = null
    @SerializedName("daily")
    @Expose
    var daily: Daily? = null
}