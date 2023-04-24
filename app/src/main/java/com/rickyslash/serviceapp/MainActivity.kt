package com.rickyslash.serviceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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