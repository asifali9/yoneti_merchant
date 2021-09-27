package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.Notifications
import com.example.yonetimerchant.databinding.MessageNotificationSingleItemBinding
import com.example.yonetimerchant.fragments.notifications.NotificationFragment

class NotificationAdapter(var notificationsList: MutableList<Notifications>,var fragmentInstance: NotificationFragment) :
    RecyclerView.Adapter<NotificationViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        context = parent.context!!
        return NotificationViewHolder(
            MessageNotificationSingleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
//        holder.bindingView.tvName.text = notificationsList.get(position).notificationText
//        if (!notificationsList.get(position).notificationType.equals("swap")) {
//            holder.bindingView.btnAccept.visibility = View.GONE
//            holder.bindingView.btnCancel.visibility = View.GONE
//        }
//
//        holder.bindingView.btnAccept.setOnClickListener {
//            fragmentInstance.acceptOrRejectSwap(true,notificationsList.get(position).notificationId)
//        }
//        holder.bindingView.btnCancel.setOnClickListener {
//            fragmentInstance.acceptOrRejectSwap(
//                false,
//                notificationsList.get(position).notificationId
//            )
//        }
    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }
}

class NotificationViewHolder(val bindingView: MessageNotificationSingleItemBinding) :
    RecyclerView.ViewHolder(bindingView.root)
