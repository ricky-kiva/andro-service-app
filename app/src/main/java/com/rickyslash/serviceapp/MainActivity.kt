package com.rickyslash.serviceapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rickyslash.serviceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val serviceIntent = Intent(this, MyBackgroundService::class.java)
        val foregroundServiceIntent = Intent(this, MyForegroundService::class.java)

        // set buttons to start/stop Services
        binding.btnStartBackgroundService.setOnClickListener {
            startService(serviceIntent)
        }
        binding.btnStopBackgroundService.setOnClickListener {
            stopService(serviceIntent)
        }
        binding.btnStartForegroundService.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 26) {
                startForegroundService(foregroundServiceIntent)
            } else {
                startService(foregroundServiceIntent)
            }
        }
        binding.btnStopForegroundService.setOnClickListener {
            stopService(foregroundServiceIntent)
        }
    }
}

// Service is a component without interface that runs variety of process that takes a long time
// examples: push notification, messaging services, music player

// Permission is a way to ask permit to user, to access some device's feature
// Permission asks for:
// - Restricted data (system status, contact information, etc)
// - Restricted actions (connect to another device, record sound, use camera)

// 2 timing in requesting permission:
// - install-time permission: app will allow any permission that is already registered when it's installed. The subtypes are:
// --- normal permission: allow app to access user's data/actions with relatively minimal risk to user's privacy (internet access)
// --- signature permission: allow app to access data/actions from another app (and both app needs to sign the same certificate (same keystore))
// - runtime permission: app will process permission request when some feature is triggered
// --- the risk is `dangerous` because it has access to `private user data` (location, contact, camera, etc)
// - special permission: commonly only owned for certain app operation, or for platform/OEM apps
// --- most of user-toggleable operations inside app's setting is special permission (example: drawing over apps)

// Registering a permission need to be done in AndroidManifest.xml
// - example: <uses-permission android:name="android.permission.INTERNET"/>

// Permission helps Android to support user's privacy:
// - Control: user has control over the data that is being shared to the app
// - Transparency: user understand what data is being used to the app
// - Data Minimization: App access & use data only when needed / instructed

// To increase the potential of allowed permissions, here are some best practices:
// - ask for minimal permission
// - bind runtime permission with some action
// - consider app's dependency
// - do transparently
// - make access to system explicitly

// If user refuse app to have access to some data, here are some best practice:
// - Give guidance to user: highlight some feature that has limited functionality if the permission not granted (give them error message)
// - Explain clearly
// - Don't give error message in fullscreen

// Service don't have interface, but run on main thread
// example of services: push notification, screen stand by, messaging services, audio player service, etc

// 3 types of Service:
// - Foreground: can run some operation that could be seen by user (audio player & it's notification). It runs even when user doesn't interact with the app
// - Background: can run in background (get user location)
// - Bound: service that being run by another component, but bind to the app (example: client-server interaction). It only stops when there is no other component that is binded (example: on streaming music service)

// every Kotlin class is a service when Inherits/Extends to Service/IntentService class
// it has it's own lifecycle based on it's types: https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/academy/dos:10a5f27b851604c94a077e64bdc4e2a920220323135537.png
// to run service from another component such Activity, use `startService(Intent)`
// to stop service, use `stopService(Intent)` or `stopSelf()` from that Service