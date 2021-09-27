package com.example.yonetimerchant.base

import android.app.Application
import com.example.yonetimerchant.utils.TypefaceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        TypefaceUtil.overrideFont(applicationContext,"SERIF","fonts/testing_font.ttf")
    }
}