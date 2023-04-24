package com.rickyslash.serviceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    companion object {
        internal val TAG = MyBackgroundService::class.java.simpleName
    }

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    // this will triggered when this service is started
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "My service is running!")
        // `START_STICKY` will make the system to restart the service if it's killed, but won't redeliver original Intent
        return START_STICKY
    }
}