package com.mrz.irankart

import android.app.Application
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp
import java.net.InetAddress

@HiltAndroidApp
class App :Application(){

    override fun onCreate() {
        super.onCreate()
    }

    fun isNetworkConnected(): Boolean {
        return try {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        } catch (e:java.lang.Exception){
            false
        }
    }

    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("google.com")
            //You can replace it with your name
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }

}