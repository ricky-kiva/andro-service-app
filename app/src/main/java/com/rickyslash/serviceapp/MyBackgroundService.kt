package com.rickyslash.serviceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyBackgroundService : Service() {

    private val serviceJob = Job()
    // this means it runs on main thread, but the lifecycle is tied to this Service
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    // this will triggered when this service is started
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "My service is running!")
        serviceScope.launch {
            for (i in 1..20) {
                delay(1000)
                Log.d(TAG, "Do something $i")
            }
            stopSelf()
            Log.d(TAG, "Service is stopped!")
        }
        // `START_STICKY` will make the system to restart the service if it's killed, but won't redeliver original Intent
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "Service disbanded.")
    }

    companion object {
        internal val TAG = MyBackgroundService::class.java.simpleName
    }

}