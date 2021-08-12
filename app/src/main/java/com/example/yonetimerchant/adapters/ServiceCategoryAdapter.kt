package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yoneti.model.Categories
import com.example.yonetimerchant.databinding.CategoriesSingleItemBinding
import com.example.yonetimerchant.fragments.creating_service.CreateServiceFragment
import java.util.ArrayList

class ServiceCategoryAdapter(
    var categories: ArrayList<Categories>,
    var instance: CreateServiceFragment.SpinnerCategoryItemsDialog
) : RecyclerView.Adapter<ServiceCategoryViewHolder>() {
    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceCategoryViewHolder {
        ctx = parent.context
        return ServiceCategoryViewHolder(
            CategoriesSingleItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ServiceCategoryViewHolder, position: Int) {
        holder.bindingView.tvCountry.text = categories.get(position).categoryName
        holder.bindingView.tvCountry.setOnClickListener {
            instance.selectedCategory(position)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}

class ServiceCategoryViewHolder(val bindingView: CategoriesSingleItemBinding) :
    RecyclerView.ViewHolder(bindingView.root)