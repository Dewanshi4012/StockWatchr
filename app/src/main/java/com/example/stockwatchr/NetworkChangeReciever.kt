package com.example.stockwatchr

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Check connectivity and show a toast
        if (context != null && !MyUtil.isInternetAvailable(context)) {
            Toast.makeText(context,"Internet Connection lost! Showing cached data", Toast.LENGTH_SHORT).show()
        }
    }
}