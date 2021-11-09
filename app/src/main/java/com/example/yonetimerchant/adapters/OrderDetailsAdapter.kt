package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.ActiveOrder
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ActiveOrdersItemLayoutBinding
import com.example.yonetimerchant.databinding.SelectedServiceItemLayoutBinding
import java.util.ArrayList

class OrderDetailsAdapter(var acitveOrdersList: ArrayList<ActiveOrder>) :
    RecyclerView.Adapter<OrderDetailsViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
        ctx = parent.context
        return OrderDetailsViewHolder(
            SelectedServiceItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
        holder.bindingView.tvServiceTime.text = acitveOrdersList.get(position).serviceTime
        holder.bindingView.tvService.text = acitveOrdersList.get(position).serviceDetails
//        holder.bindingView.tvServiceTime.text = (acitveOrdersList.get(position).endTime!!.toInt() - acitveOrdersList.get(position).startTime!!.toInt()).toString()
        holder.bindingView.tvServicePrice.text = acitveOrdersList.get(position).servicePrice
    }

    override fun getItemCount(): Int {
        return acitveOrdersList.size
    }
}

class OrderDetailsViewHolder(val bindingView: SelectedServiceItemLayoutBinding) :
    RecyclerView.ViewHolder(bindingView.root)