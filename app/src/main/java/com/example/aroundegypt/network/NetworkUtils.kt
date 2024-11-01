package com.example.aroundegypt.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

object NetworkUtils {
    fun isNetworkAvailable(context: Context): Network? {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetwork
    }
}

