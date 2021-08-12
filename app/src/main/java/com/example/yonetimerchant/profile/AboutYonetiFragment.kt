package com.example.yonetimerchant.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yonetimerchant.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutYonetiFragment : Fragment() {

    private lateinit var ivBack: ImageView
    var aboutView:View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutView = inflater.inflate(R.layout.fragment_about_yoneti,container,false)
        ivBack = aboutView!!.findViewById(R.id.imageView16)
        ivBack.setOnClickListener {
            Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        return aboutView
    }
}