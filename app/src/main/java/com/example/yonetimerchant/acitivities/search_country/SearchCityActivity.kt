package com.example.yoneti.activities.search_country

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.yoneti.model.City
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.SearchCityAdapter
import com.example.yonetimerchant.databinding.ActivitySearchLocationInCityBinding
import com.example.yonetimerchant.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityActivity : AppCompatActivity() {
    private lateinit var countryId: String
    lateinit var searchBinding: ActivitySearchLocationInCityBinding
    val locationViewModel: SearchLocationViewModel by viewModels()
    private lateinit var cityAdapter: SearchCityAdapter
    var listOfCities = ArrayList<City>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search_location_in_city)

        countryId = intent.getStringExtra(Constants.COUNTRY_ID)!!

        searchBinding.etCitySearch.addTextChangedListener {
            if (it.toString().trim().isNotEmpty())
                locationViewModel.searchCity(it.toString().trim(),countryId)
        }

        locationViewModel.cityList.observe(this, Observer {
            if (!this::cityAdapter.isInitialized) {
                listOfCities = it
                cityAdapter = SearchCityAdapter(listOfCities)
            } else {
                listOfCities.clear()
                listOfCities = it
                cityAdapter.notifyDataSetChanged()
            }
            searchBinding.rvCountries.adapter = cityAdapter
        })

        locationViewModel.cityError.observe(this, Observer {
            Toast.makeText(
                SearchLocationInCityActivity@ this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        })
    }
//    override fun getViewMode(): Class<SearchLocationModel> = SearchLocationModel::class.java

}