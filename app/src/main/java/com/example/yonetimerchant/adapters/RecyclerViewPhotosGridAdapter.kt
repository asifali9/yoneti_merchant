package com.example.yonetimerchant.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yoneti.model.GridImage
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.PhotoGridItemBinding
import com.example.yonetimerchant.databinding.StaticPhotoGridItemBinding
import com.example.yonetimerchant.fragments.photos_fragment.GridPhotosFragment

class RecyclerViewPhotosGridAdapter(
    var photosList: MutableList<GridImage>,
    val fragmentInstance: GridPhotosFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        if (viewType == UPLOAD_IMAGE)
            return StaticPhotoGridViewHolder(
                StaticPhotoGridItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            return PhotoGridViewHolder(
                PhotoGridItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        return PhotoGridViewHolder(
            PhotoGridItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StaticPhotoGridViewHolder){
//            Glide.with(context)
//                .load(R.mipmap.ic_launcher)
//                .into(holder.bindingView.ivCategory)

            holder.bindingView.ivCategory.setOnClickListener {
                Toast.makeText(context, "opening gallery", Toast.LENGTH_SHORT).show()
                fragmentInstance.openGallery()
            }
        }
        else if (holder is PhotoGridViewHolder){
            Glide.with(context)
                .load(photosList.get(position).imagePath)
                .into(holder.bindingView.ivCategory)


            holder.bindingView.ivCategory.setOnClickListener {
                fragmentInstance.showPhoto(photosList.get(position).imagePath)
            }
        }
    }

    override fun getItemCount() :Int{
        if (photosList.size == 0)
            return 1
        else
            return photosList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return UPLOAD_IMAGE
        return CELL_IMAGES
    }

    val UPLOAD_IMAGE = 1
    val CELL_IMAGES = 2
}

class PhotoGridViewHolder(val bindingView: PhotoGridItemBinding) :
    RecyclerView.ViewHolder(bindingView.root)

class StaticPhotoGridViewHolder(val bindingView: StaticPhotoGridItemBinding) :
    RecyclerView.ViewHolder(bindingView.root)
