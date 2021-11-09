package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.tracking.QueueOrdersFragment
import com.example.yonetimerchant.model.QueueOrders
import java.util.ArrayList

class QueueOrdersAdapter(
    var instance: QueueOrdersFragment,
    var orders: ArrayList<QueueOrders>
) : RecyclerView.Adapter<QueueOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueOrdersViewHolder {
        ctx = parent.context
        return QueueOrdersViewHolder(
            ProgressOrdersItemTrackingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: QueueOrdersViewHolder, position: Int) {
        Glide.with(ctx)
            .load(orders.get(position).picUrl)
            .into(holder.bindingView.imgProfile)

        holder.bindingView.tvOrderDate.text = orders.get(position).date
        holder.bindingView.tvUserName.text = orders.get(position).userName
        holder.bindingView.tvServiceName.text = orders.get(position).orderDetails

        holder.bindingView.rootView.setOnClickListener {
            instance.openingDetails(
                orders.get(position).orderId
            )
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}

class QueueOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)