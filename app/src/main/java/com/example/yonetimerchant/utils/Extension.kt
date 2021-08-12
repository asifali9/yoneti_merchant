package com.example.yonetimerchant.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast


fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.openActivity(t: Class<*>) {
    startActivity(Intent(this, t))
}

fun View.show()
{
    this.visibility = View.VISIBLE
}

fun View.hide()
{
    this.visibility = View.GONE
}
