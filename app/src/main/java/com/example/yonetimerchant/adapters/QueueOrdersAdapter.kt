package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yonetimerchant.databinding.ProgressOrdersItemTrackingBinding
import com.example.yonetimerchant.fragments.tracking.QueueOrdersFragment

class QueueOrdersAdapter(
    var instance: QueueOrdersFragment
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
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class QueueOrdersViewHolder(val bindingView: ProgressOrdersItemTrackingBinding) :
    RecyclerView.ViewHolder(bindingView.root)