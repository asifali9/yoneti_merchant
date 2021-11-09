package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.ActiveOrder
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.orders.ListOfOrdersFragment
import com.example.yonetimerchant.fragments.tracking.ProgressOrdersFragment
import java.util.ArrayList

class ActiveOrdersAdapter(
    var acitveOrdersList: ArrayList<ActiveOrder>,
    var listOrderFragmentInstance: ListOfOrdersFragment?,
    var instance: ProgressOrdersFragment?,
) :
    RecyclerView.Adapter<ActiveOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrdersViewHolder {
        ctx = parent.context
        return ActiveOrdersViewHolder(
            ProgressOrdersItemTrackingBinding.inflate(
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
        holder.bindingView.tvUserName.text = acitveOrdersList.get(position).userName
        holder.bindingView.tvServiceName.text = acitveOrdersList.get(position).orderDetails

        holder.bindingView.rootView.setOnClickListener {
            instance!!.openingDetails(
            acitveOrdersList.get(position).categoryId
            )
        }
    }

    override fun getItemCount(): Int {
        return acitveOrdersList.size
    }
}

class ActiveOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)