package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.Categories
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.tracking.CancelOrdersFragment
import com.example.yonetimerchant.fragments.tracking.ProgressOrdersFragment
import java.util.ArrayList

class CancelOrdersAdapter(
    var instance: CancelOrdersFragment
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
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class CancelOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)