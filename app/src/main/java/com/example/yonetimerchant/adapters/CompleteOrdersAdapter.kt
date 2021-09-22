package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.tracking.CompleteOrdersFragment

class CompleteOrdersAdapter(
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
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class CompleteOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)