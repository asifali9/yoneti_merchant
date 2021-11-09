package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.ActiveOrder
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.tracking.CompleteOrdersFragment
import java.util.ArrayList

class CompleteOrdersAdapter(
    var completeOrders: ArrayList<ActiveOrder>,
    var instance: CompleteOrdersFragment
) : RecyclerView.Adapter<CompleteOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompleteOrdersViewHolder {
        ctx = parent.context
        return CompleteOrdersViewHolder(
            ProgressOrdersItemTrackingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CompleteOrdersViewHolder, position: Int) {
        holder.bindingView.tvOrderManageOrViewAction.text = ctx.resources.getString(R.string.view_order)
        Glide.with(ctx)
            .load(completeOrders.get(position).userPic)
            .placeholder(R.drawable.img_profile_placeholder)
            .into(holder.bindingView.imgProfile)

        holder.bindingView.tvOrderDate.text = completeOrders.get(position).date
        holder.bindingView.tvUserName.text = completeOrders.get(position).userName
        holder.bindingView.tvServiceName.text = completeOrders.get(position).orderDetails

        holder.bindingView.rootView.setOnClickListener {
            instance.openingDetails(completeOrders.get(position).categoryId)
        }
    }

    override fun getItemCount(): Int {
        return completeOrders.size
    }
}

class CompleteOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)