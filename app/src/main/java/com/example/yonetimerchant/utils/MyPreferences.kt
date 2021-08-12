package com.example.yonetimerchant.utils

import android.content.Context
import com.example.yoneti.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPreferences @Inject constructor(@ApplicationContext var context: Context){
    val pref = context.getSharedPreferences("yoneti", Context.MODE_PRIVATE)
    val editor = pref.edit()

    fun test(flag:Int) = editor.putInt("testing",flag).apply()
    fun setUser(user: User) = editor.putString("user",user.toString()).commit()

    /**
     * for introductory pager adapter
     */
    fun introduced(b: Boolean) =  editor.putBoolean("isIntroduced",b).apply()
    fun isIntroduced() = pref.getBoolean("isIntroduced",false)

    fun isUserLogin() = pref.getBoolean("isLoggedIn",false)
    fun setLogin(login:Boolean) = editor.putBoolean("isLoggedIn",login).commit()
    fun setUserId(userId: Int) = editor.putInt("userId",userId).commit()
    fun getUserId() = pref.getInt("userId",0)

    /**
     * Login session id
     */
    fun setSessionId(id:String) = editor.putString("sessionId",id)
    fun getSessionId() = pref.getString("sessionId","0")

    /**
     * save complete user while login and signup
     */
    fun setUser(user: String?) = editor.putString("userData",user!!).commit()
    fun getUser() = pref.getString("userData",null)
    fun setDeviceToken(token: String) {
        editor.putString("deviceToken",token).commit()
    }
    fun getDeviceToken() = pref.getString("deviceToken","")
}