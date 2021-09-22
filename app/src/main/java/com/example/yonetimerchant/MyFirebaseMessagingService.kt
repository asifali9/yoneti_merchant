package com.example.yonetimerchant

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = MyFirebaseMessagingService::class.java.simpleName
    override fun onMessageReceived(p0: RemoteMessage) {
        Log.d(TAG, "onMessageReceived: ")
    }
}