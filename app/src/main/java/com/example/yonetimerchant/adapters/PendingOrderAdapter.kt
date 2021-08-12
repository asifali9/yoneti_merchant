package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ActiveOrderSingleItemBinding
import com.example.yonetimerchant.fragments.complete_profile.CompleteProfileFragment
import com.example.yonetimerchant.model.QueueOrders

class PendingOrderAdapter(
    var queueOrdersList: ArrayList<QueueOrders>,
    var completeProfileFragment: CompleteProfileFragment
) :
    RecyclerView.Adapter<BookingInQueueViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingInQueueViewHolder {
        ctx = parent.context
        return BookingInQueueViewHolder(
            ActiveOrderSingleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookingInQueueViewHolder, position: Int) {
        Glide.with(ctx)
            .load(queueOrdersList.get(position).picUrl)
            .placeholder(R.drawable.ic_face)
            .into(holder.bindingView.imageView2)

        holder.bindingView.tvUserName.setText(queueOrdersList.get(position).userName)
        holder.bindingView.tvOrderDetails.setText(queueOrdersList.get(position).orderDetails)
        holder.bindingView.tvTime.setText(queueOrdersList.get(position).startTime)
        holder.bindingView.tvDate.setText(queueOrdersList.get(position).date)

        holder.bindingView.activeOrderRootView.setOnClickListener {
            completeProfileFragment.openPendingOrder(queueOrdersList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return queueOrdersList.size
    }
}

class BookingInQueueViewHolder(val bindingView: ActiveOrderSingleItemBinding) :
    RecyclerView.ViewHolder(bindingView.root)