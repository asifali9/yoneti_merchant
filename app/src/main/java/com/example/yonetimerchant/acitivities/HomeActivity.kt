
package com.example.yonetimerchant.acitivities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yoneti.activities.login.LoginActivity
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ActivityHomeBinding
import com.example.yonetimerchant.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    lateinit var nav: NavController
    private lateinit var homeBinding: ActivityHomeBinding
    var list = listOf<String>("pakistan")
    var list2 = listOf<String>("hello", "world")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        homeBinding.bottomNavigationView.setupWithNavController(findNavController(R.id.fragment))
        nav = Navigation.findNavController(this,R.id.fragment)
        GlobalScope.launch(Dispatchers.IO){
            var data = async { sum1() }
            withContext(Dispatchers.Main){
                Toast.makeText(this@HomeActivity, "${data.await()}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getBottomNavigation() = homeBinding.bottomNavigationView
    suspend fun sum1(): List<String> {
        delay(15000)
        return list
    }
    suspend fun sum2(): List<String> {
        delay(2000)
        return list2
    }

    fun signOutclick() {
        openActivity(LoginActivity::class.java)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 999 && resultCode == RESULT_OK)
        {
            data
        }
    }
}