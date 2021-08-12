package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.MerchantServices
import com.example.yonetimerchant.databinding.RowServiceNameBinding
import com.example.yonetimerchant.fragments.creating_service.CreateServiceFragment
import java.util.ArrayList

class MerchantServicesAdapter(
    var merchantServices: ArrayList<MerchantServices>,
    var instance: CreateServiceFragment
) : RecyclerView.Adapter<MerchantServicesViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchantServicesViewHolder {
        ctx = parent.context
        return MerchantServicesViewHolder(
            RowServiceNameBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MerchantServicesViewHolder, position: Int) {
        holder.bindingView.tvServicename.text = merchantServices.get(position).serviceTitle
        holder.bindingView.tvPriceAndTime.text = merchantServices.get(position).serviceCharged+"$-"+merchantServices.get(position).estimatedTime+"min"
        holder.bindingView.etEditService.setOnClickListener {
            instance.updateService(merchantServices.get(position),position)
        }
    }

    override fun getItemCount(): Int {
        return merchantServices.size
    }
}

class MerchantServicesViewHolder(val bindingView: RowServiceNameBinding) :
    RecyclerView.ViewHolder(bindingView.root)