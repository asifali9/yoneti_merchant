package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.complete_profile.CompleteProfileFragment
import com.example.yonetimerchant.model.InProgressOrders
import kotlin.collections.ArrayList

class ProgressOrdersAdapter(
    var inProgressOrders: MutableList<InProgressOrders>,
    var completeProfileFragment: CompleteProfileFragment
) : RecyclerView.Adapter<ProgressOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressOrdersViewHolder {
        ctx = parent.context
        return ProgressOrdersViewHolder(
            ProgressOrdersItemTrackingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProgressOrdersViewHolder, position: Int) {
        Glide.with(ctx)
            .load(inProgressOrders.get(position).picUrl)
            .placeholder(R.drawable.img_profile_placeholder)
            .into(holder.bindingView.imgProfile)
        holder.bindingView.tvOrderDate.text = inProgressOrders.get(position).date
        holder.bindingView.tvServiceName.text = inProgressOrders.get(position).orderDetails
        holder.bindingView.tvUserName.text = inProgressOrders.get(position).userName

        holder.bindingView.tvRating.setOnClickListener {
            completeProfileFragment.openOrderDetail()
        }
    }

    override fun getItemCount(): Int {
        return inProgressOrders.size
    }
}

class ProgressOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)