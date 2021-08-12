package com.example.yonetimerchant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.Comments
import com.example.yonetimerchant.databinding.CommentItemBinding

class CommentsAdapter(var listOfCountries:ArrayList<Comments>): RecyclerView.Adapter<CommentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(CommentItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
//        holder.bindingView.tvCountry.text = listOfCountries.get(position).countryName
        holder.bindingView.tvName.text = listOfCountries.get(position).comment
    }

    override fun getItemCount(): Int {
        return 10
    }
}

class CommentsViewHolder(val bindingView: CommentItemBinding) : RecyclerView.ViewHolder(bindingView.root)