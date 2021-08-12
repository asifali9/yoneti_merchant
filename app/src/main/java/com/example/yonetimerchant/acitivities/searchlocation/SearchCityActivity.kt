package com.example.yoneti.fragments.searchlocation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ChangeAddressAdapter
import com.example.yonetimerchant.databinding.ActivitySearchLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityActivity:AppCompatActivity() {
    lateinit var searchBinding: ActivitySearchLocationBinding
    private lateinit var addressAdapter: ChangeAddressAdapter
    var listOfCountires = ArrayList<Countries>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_location)
//        for (i in 1..10)
//            listOfCountires.add(Countries())
//        if (!this::addressAdapter.isInitialized) {
//            addressAdapter = ChangeAddressAdapter(listOfCountires)
//            searchBinding.rvCountries.adapter = addressAdapter
//        }
    }
//    override fun getViewMode(): Class<SearchLocationModel> = SearchLocationModel::class.java

}