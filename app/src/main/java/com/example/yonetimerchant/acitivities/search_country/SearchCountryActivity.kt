package com.example.yoneti.activities.search_country

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ChangeAddressAdapter
import com.example.yonetimerchant.databinding.ActivitySearchLocationInCityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCountryActivity : AppCompatActivity() {
    lateinit var searchBinding: ActivitySearchLocationInCityBinding
    val locationViewModel: SearchLocationViewModel by viewModels()
    private lateinit var addressAdapter: ChangeAddressAdapter
    var listOfCountires = ArrayList<Countries>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_search_location_in_city)

        searchBinding.etCitySearch.addTextChangedListener {
            if (it.toString().trim().isNotEmpty())
                locationViewModel.searchCountry(it.toString().trim())
        }

        locationViewModel.countryList.observe(this, Observer {
            if (!this::addressAdapter.isInitialized) {
                listOfCountires = it
                addressAdapter = ChangeAddressAdapter(listOfCountires, null,this)
            } else {
                listOfCountires.clear()
                listOfCountires = it
                addressAdapter.notifyDataSetChanged()
            }
            searchBinding.rvCountries.adapter = addressAdapter
        })

        locationViewModel.countryError.observe(this, Observer {
            Toast.makeText(
                SearchLocationInCityActivity@ this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        })
    }
//    override fun getViewMode(): Class<SearchLocationModel> = SearchLocationModel::class.java

}