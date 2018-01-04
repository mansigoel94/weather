package com.example.mansigoel.weather

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat


class Utils {
    companion object {

        @SuppressLint("MissingPermission")
        public fun getLatitude(context: Context): Double {
            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                return location.latitude;
            } else {
                var latitude: Double = 0.0

                val locationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        if (location!= null)
                            latitude = location.latitude
                        else
                            Log.d("Mansi","location is null")
                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                        //We don't need this method for current implementation
                    }

                    override fun onProviderEnabled(p0: String?) {
                        //We don't need this method for current implementation
                    }

                    override fun onProviderDisabled(p0: String?) {
                        //We don't need this method for current implementation
                    }
                }
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        2000L, 0f, locationListener);

                return latitude;
            }

        }

        @SuppressLint("MissingPermission")
        public fun getLongitude(context: Context): Double {
            val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                return location.longitude;
            } else {
                var longitude: Double = 0.0

                val locationListener = object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        if (location != null) {
                            longitude = location.longitude
                        };
                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                        //We don't need this method for current implementation
                    }

                    override fun onProviderEnabled(p0: String?) {
                        //We don't need this method for current implementation
                    }

                    override fun onProviderDisabled(p0: String?) {
                        //We don't need this method for current implementation
                    }
                }

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        2000L, 0f, locationListener);

                return longitude;
            }
        }

        public fun getWeekday(timeMillis: Long): String {
            val dateFormat: String = "EEEE"
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat(dateFormat)

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeMillis
            return formatter.format(calendar.time)
        }

        public fun getHour(timeMillis: Long): String {
            val dateFormat: String = "h a"
            val dateFormatMatcher: String = "dd MM H a"
            val formatter = SimpleDateFormat(dateFormat)
            val formatterMatcher = SimpleDateFormat(dateFormatMatcher)

            val calendar = Calendar.getInstance()

            //getting current time from system in H a format
            val currentTime = formatterMatcher.format(calendar.time)
            Log.d("Mansi", "current time: " + currentTime)

            //setting parameter timeMillis to calendar instance
            calendar.timeInMillis = timeMillis
            val paramTime = formatterMatcher.format(calendar.time)
            Log.d("Mansi", "param time: " + paramTime)

            if (currentTime.equals(paramTime))
                return "NOW"
            return formatter.format(calendar.time)
        }

        public fun getTime(timeMillis: Long): String{
            val dateFormat: String = "h:mm a"
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat(dateFormat)

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeMillis
            return formatter.format(calendar.time)
        }

        public fun getImage(string: String?): Int {
            when (string) {
                "blue-hour" -> return R.drawable.blue_hour
                "clear-day" -> return R.drawable.clear_day
                "clear-night" -> return R.drawable.clear_night
                "cloudy" -> return R.drawable.cloudy
                "evening" -> return R.drawable.evening
                "fog" -> return R.drawable.fog
                "golden-hour" -> return R.drawable.golden_hour
                "hail" -> return R.drawable.hail
                "hamburger-menu" -> return R.drawable.hamburger_menu
                "morning" -> return R.drawable.morning
                "na" -> return R.drawable.na
                "partly-cloudy-day" -> return R.drawable.partly_cloudy_day
                "partly-cloudy-night" -> return R.drawable.partly_cloudy_night
                "rain" -> return R.drawable.rain
                "sleet" -> return R.drawable.sleet
                "snow" -> return R.drawable.snow
                "sunrise" -> return R.drawable.sunrise
                "sunset" -> return R.drawable.sunset
                "thunderstorm" -> return R.drawable.thunderstorm
                "tornado" -> return R.drawable.tornado
                "thunderstorm" -> return R.drawable.thunderstorm
                "tornado" -> return R.drawable.tornado
                "wind" -> return R.drawable.wind
                else -> {
                    return R.drawable.default1
                }
            }
        }
    }

}