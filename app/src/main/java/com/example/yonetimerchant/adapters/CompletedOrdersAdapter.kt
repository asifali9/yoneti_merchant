package com.example.yonetimerchant.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.ActiveOrder
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.CompletedOrdersItemLayoutBinding
import com.example.yonetimerchant.fragments.complete_profile.CompleteProfileFragment
import com.example.yonetimerchant.fragments.orders.ListOfOrdersFragment

class CompletedOrdersAdapter(
    var completedOrdersList: MutableList<ActiveOrder>,
    var instance: ListOfOrdersFragment?,
    var completeProfileFragment: CompleteProfileFragment?
) :
    RecyclerView.Adapter<CompletedOrdersViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedOrdersViewHolder {
        ctx = parent.context
        return CompletedOrdersViewHolder(
            CompletedOrdersItemLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CompletedOrdersViewHolder, position: Int) {
        holder.bindingView.tvUserName.setText(completedOrdersList.get(position).userName)
        holder.bindingView.tvService.setText(completedOrdersList.get(position).orderDetails)
        holder.bindingView.tvRatingCount.setText(completedOrdersList.get(position).merchantRating)
        var progress = holder.bindingView.rateBar.progressDrawable
        if (completedOrdersList.get(position).merchantRating!!.toFloat() >= 1f && completedOrdersList.get(position).merchantRating!!.toFloat() < 3f)
        {
            DrawableCompat.setTint(progress,Color.RED)
        }else if  (completedOrdersList.get(position).merchantRating!!.toFloat() >= 3f && completedOrdersList.get(position).merchantRating!!.toFloat() < 4f)
        {
            DrawableCompat.setTint(progress,Color.YELLOW)
        }else
        {
            DrawableCompat.setTint(progress,Color.WHITE)
        }
//        holder.bindingView.rateBar.fill
        Glide.with(ctx)
            .load(completedOrdersList.get(position).userPic)
            .placeholder(R.drawable.ic_face)
            .into(holder.bindingView.imageView2)

        holder.bindingView.rateBar.setRating(completedOrdersList.get(position).merchantRating?.toFloat()!!)

        holder.bindingView.completeOrderViewGroup.setOnClickListener {
            /**
             * opening fragment from list of orders
             */
//            instance?.openOrderDetail()
            /**
             * opening fragment from dashboard
             */
            completeProfileFragment?.openOrderDetail(completedOrdersList.get(position).categoryId)
        }
    }

    override fun getItemCount(): Int {
        return completedOrdersList.size
    }
}

class CompletedOrdersViewHolder(val bindingView: CompletedOrdersItemLayoutBinding) :
    RecyclerView.ViewHolder(bindingView.root)