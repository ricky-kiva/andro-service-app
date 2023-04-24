package com.rickyslash.serviceapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

// BoundService allows app to send data to service (using Intent) and get data from service
class MyBoundService : Service() {

    // this object is returned on onBind()
    private var binder = MyBinder()

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    // LiveData is used to pass countdown data from MyBoundService to MainActivity
    val numberLiveData: MutableLiveData<Int> = MutableLiveData()

    // this class provides way for Activity to obtain reference to the service instance
    internal inner class MyBinder: Binder() {
        val getService: MyBoundService = this@MyBoundService
    }

    // onBinding will bound to something (example: bound to Activity)
    override fun onBind(intent: Intent): IBinder {
       Log.d(TAG, "onBind: ")
        serviceScope.launch {
            for (i in 1..20) {
                delay(1000)
                Log.d(TAG, "Do something $i")
                // this to update value of livedata
                numberLiveData.postValue(i)
            }
            Log.d(TAG, "Service stopped!")
        }
        return binder
    }

    // this will call after unbind
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    //
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        serviceJob.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }

    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }

}