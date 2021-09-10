package com.example.yonetimerchant.fragments.photos_fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.GridImage
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.RecyclerViewPhotosGridAdapter
import com.example.yonetimerchant.databinding.FragmentGridPhotosBinding
import com.example.yonetimerchant.utils.Constants.MERCHANT_SELECTED_IMAGE
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException

@AndroidEntryPoint
class GridPhotosFragment(var position: Int, var albumId: String) :
    BaseFragment<GridPhotosViewModel, FragmentGridPhotosBinding>() {
    private lateinit var gridPhotosAdapter: RecyclerViewPhotosGridAdapter
    private val REQUEST_CODE: Int = 100
    private lateinit var nav: NavController
    var pageNumber = 0
    var pageSize = 10

    override fun getLayout() = R.layout.fragment_grid_photos

    override fun bindingToViews() {
        if (position == 0)
            viewModel!!.getAllUploadedPhotos(pageNumber, pageSize)
        else
            viewModel!!.getPhotos(albumId, pageNumber, pageSize)
//        nav = Navigation.findNavController(requireActivity(),R.id.service_viewer)
        viewModel!!.gridImages.observe(this, Observer {
            var list = it
            if (position != 0)
            list.add(0,GridImage("", "", "", ""))
            gridPhotosAdapter = RecyclerViewPhotosGridAdapter(list, this,position)
            binding.rvGridPhotos.adapter = gridPhotosAdapter
        })
    }

    override fun getViewMode(): Class<GridPhotosViewModel> = GridPhotosViewModel::class.java

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        var listSize = gridPhotosAdapter.photosList.size
                        gridPhotosAdapter.photosList.add(GridImage("", data.dataString!!, "", ""))
                        gridPhotosAdapter.notifyItemInserted(listSize)
                        viewModel!!.uploadPhoto(
                            requireContext().contentResolver.openInputStream(
                                data.data!!
                            ),
                            albumId
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(activity, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun showPhoto(imagePath: String) {
        var imagePathBundle = Bundle()
        imagePathBundle.putString(MERCHANT_SELECTED_IMAGE, imagePath)
        findNavController().navigate(R.id.merchantSinglePhotoFragment, imagePathBundle)
    }

}