package com.example.yonetimerchant.acitivities

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ActivitySplashBinding
import com.example.yonetimerchant.utils.MyPreferences
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var activitySplash: ActivitySplashBinding
    val TAG = SplashActivity::class.java.simpleName

    @Inject
    lateinit var preferences: MyPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplash = DataBindingUtil.setContentView<ActivitySplashBinding>(SplashActivity@this,R.layout.activity_splash)
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d(TAG, token!!)
            Toast.makeText(baseContext, token!!, Toast.LENGTH_SHORT).show()
            if(preferences.getUser().isNullOrEmpty()!!)
                preferences.setDeviceToken(token!!)
        })

        Handler().postDelayed(Runnable {
            if (!preferences.isUserLogin())
                startActivity(Intent(SplashActivity@ this, IntroductoryActivity::class.java))
            else
                startActivity(Intent(SplashActivity@ this, HomeActivity::class.java))
            finish()
        }, 3000)

        setUI()
    }

    private fun setUI() {
//        var bitmapIcon = BitmapFactory.decodeResource(resources,R.drawable.ic_bottom_curve)
//        activitySplash.ivBottomCurve.setImageBitmap(bitmapIcon)
    }
}