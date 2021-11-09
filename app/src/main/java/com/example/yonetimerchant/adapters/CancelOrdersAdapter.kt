package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.ActiveOrder
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.tracking.CancelOrdersFragment
import java.util.ArrayList

class CancelOrdersAdapter(
    var instance: CancelOrdersFragment,
    var list: ArrayList<ActiveOrder>
) : RecyclerView.Adapter<CancelOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CancelOrdersViewHolder {
        ctx = parent.context
        return CancelOrdersViewHolder(
            ProgressOrdersItemTrackingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CancelOrdersViewHolder, position: Int) {
        holder.bindingView.tvOrderManageOrViewAction.text = ctx.resources.getString(R.string.view_order)
        Glide.with(ctx)
            .load(list.get(position).userPic)
            .placeholder(R.drawable.img_profile_placeholder)
            .into(holder.bindingView.imgProfile)

        holder.bindingView.tvOrderDate.text = list.get(position).date
        holder.bindingView.tvUserName.text = list.get(position).userName
        holder.bindingView.tvServiceName.text = list.get(position).orderDetails
//        holder.bindingView.tvOrdersCount.text = list.get(position).

        holder.bindingView.rootView.setOnClickListener {
            instance.openingDetails(list.get(position).categoryId)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class CancelOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)