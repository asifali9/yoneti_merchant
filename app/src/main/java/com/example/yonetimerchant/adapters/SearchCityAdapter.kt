package com.example.yonetimerchant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.City
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.databinding.LocationItemBinding

class SearchCityAdapter(var listOfCities:ArrayList<City>): RecyclerView.Adapter<CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(LocationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindingView.tvCountry.text = listOfCities.get(position).countryName
        holder.bindingView.ivFlag.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return listOfCities.size
    }
}

class CityViewHolder(val bindingView: LocationItemBinding) : RecyclerView.ViewHolder(bindingView.root)