package com.example.yonetimerchant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.activities.search_country.SearchCountryActivity
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.databinding.LocationItemBinding
import com.example.yonetimerchant.dialog_fragment.ChangeAddressDialogFragment

class ChangeAddressAdapter(
    var listOfCountries: ArrayList<Countries>,
    var instance: ChangeAddressDialogFragment?,
    var searchCountryActivity: SearchCountryActivity?
): RecyclerView.Adapter<AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(LocationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bindingView.tvCountry.text = listOfCountries.get(position).countryName
        if (instance is ChangeAddressDialogFragment)
            instance?.selectCountry(listOfCountries.get(position).countryName)
    }

    override fun getItemCount(): Int {
        return listOfCountries.size
    }
}

class AddressViewHolder(val bindingView: LocationItemBinding) : RecyclerView.ViewHolder(bindingView.root)