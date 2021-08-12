package com.example.yonetimerchant.dialog_fragment

import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.Comments
import com.example.yoneti.model.IgnoreViewModel
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.LikesAdapter
import com.example.yonetimerchant.databinding.FragmentLikeDialogBinding

class LikesDialogFragment : BaseDialogFragment<IgnoreViewModel, FragmentLikeDialogBinding>(){
    private lateinit var likeAdapter: LikesAdapter
    var listOfComments = ArrayList<Comments>()

    override fun getLayout(): Int = R.layout.fragment_like_dialog

    override fun bindingToView() {
        for (i in 1..10)
            listOfComments.add(Comments())
        if (!this::likeAdapter.isInitialized) {

        }
        likeAdapter = LikesAdapter(listOfComments)
        dataBinding.rvLikes.adapter = likeAdapter
    }

//    override fun getViewMode(): Class<IgnoreViewModel>  = IgnoreViewModel::class.java
//
//    override fun bindingToViews() {
//        for (i in 1..10)
//            listOfComments.add(Comments())
//        if (!this::addressAdapter.isInitialized) {
//
//        }
//        addressAdapter = CommentsAdapter(listOfComments)
//        binding.rvLikes.adapter = addressAdapter
//    }
    }