package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.ActiveOrder
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ActiveOrdersItemLayoutBinding
import java.util.ArrayList

class ActiveOrdersAdapter(var acitveOrdersList: ArrayList<ActiveOrder>) :
    RecyclerView.Adapter<ActiveOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrdersViewHolder {
        ctx = parent.context
        return ActiveOrdersViewHolder(
            ActiveOrdersItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ActiveOrdersViewHolder, position: Int) {
        Glide.with(ctx)
            .load(acitveOrdersList.get(position).userPic)
            .placeholder(R.drawable.img_profile_placeholder)
            .into(holder.bindingView.imgProfile)

        holder.bindingView.tvOrderDate.text = acitveOrdersList.get(position).date
        holder.bindingView.tvUserName.text = acitveOrdersList.get(position).customerName
        holder.bindingView.tvServiceName.text = acitveOrdersList.get(position).orderDetails
    }

    override fun getItemCount(): Int {
        return acitveOrdersList.size
    }
}

class ActiveOrdersViewHolder(val bindingView: ActiveOrdersItemLayoutBinding) :
    RecyclerView.ViewHolder(bindingView.root)