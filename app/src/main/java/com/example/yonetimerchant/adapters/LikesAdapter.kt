package com.example.yonetimerchant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.Comments
import com.example.yonetimerchant.databinding.LikesItemBinding

class LikesAdapter(var listOfCountries:ArrayList<Comments>): RecyclerView.Adapter<LikesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        return LikesViewHolder(LikesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
//        holder.bindingView.tvCountry.text = listOfCountries.get(position).countryName
    }

    override fun getItemCount(): Int {
        return listOfCountries.size
    }
}

class LikesViewHolder(val bindingView: LikesItemBinding) : RecyclerView.ViewHolder(bindingView.root)