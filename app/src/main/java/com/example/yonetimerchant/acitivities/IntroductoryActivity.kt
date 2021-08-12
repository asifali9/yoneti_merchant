package com.example.yonetimerchant.acitivities

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.yoneti.activities.login.LoginActivity
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.IntroductoryPagerAdapter
import com.example.yonetimerchant.databinding.ActivityMainBinding
import com.example.yonetimerchant.utils.MyPreferences
import com.example.yonetimerchant.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class IntroductoryActivity : AppCompatActivity() {
    private lateinit var adapter: IntroductoryPagerAdapter
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var preferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         *is app already introduced or not
         */
        if (!preferences.isIntroduced()) {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

            adapter = IntroductoryPagerAdapter(supportFragmentManager)
            binding.viewPager.adapter = adapter
            binding.dotsIndicator.setupWithViewPager(binding.viewPager)
        }else
            moveToActivity()
    }

    fun onNextButtonClicked(view: View) {
        when(binding.viewPager.currentItem)
        {
            0,1,2 -> {
                binding.viewPager.setCurrentItem(++binding.viewPager.currentItem,true)
            }
            3 -> {
                moveToActivity()
            }
        }
    }

    private fun moveToActivity() {
        var i = Intent(this,LoginActivity::class.java)
        preferences.introduced(true)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(i,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }else
        openActivity(LoginActivity::class.java)
        finish()
    }
}