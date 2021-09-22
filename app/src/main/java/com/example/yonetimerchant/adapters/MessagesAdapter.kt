package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.Messages
import com.example.yonetimerchant.databinding.MessageNotificationSingleItemBinding
import com.example.yonetimerchant.fragments.notifications.MessagesFragment

class MessagesAdapter(
    var messagesList: MutableList<Messages>,
    var fragmentInstance: MessagesFragment
) :
    RecyclerView.Adapter<MessageViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        context = parent.context!!
        return MessageViewHolder(
            MessageNotificationSingleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bindingView.tvName.text = messagesList.get(position).userName
        holder.bindingView.tvMessageDescription.text = messagesList.get(position).message
        holder.bindingView.tvMessageTime.text = messagesList.get(position).messageDate
        Glide.with(context)
            .load(messagesList.get(position).avatar)
            .into(holder.bindingView.ivReviewer)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }
}

class MessageViewHolder(val bindingView: MessageNotificationSingleItemBinding) :
    RecyclerView.ViewHolder(bindingView.root)
