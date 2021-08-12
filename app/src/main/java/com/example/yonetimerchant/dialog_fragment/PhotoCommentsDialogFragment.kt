package com.example.yonetimerchant.dialog_fragment

import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.Comments
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.CommentsAdapter
import com.example.yonetimerchant.databinding.DialogFragmentPhotoCommentsBinding

class PhotoCommentsDialogFragment :BaseDialogFragment<Nothing, DialogFragmentPhotoCommentsBinding>(){
    private lateinit var addressAdapter: CommentsAdapter
    var listOfComments = ArrayList<Comments>()

    override fun getLayout(): Int = R.layout.dialog_fragment_photo_comments

    override fun bindingToView() {
        for (i in 1..10)
            listOfComments.add(Comments())
//        if (!this::addressAdapter.isInitialized) {
        addressAdapter = CommentsAdapter(listOfComments)
        dataBinding.rvComments.adapter = addressAdapter
//        }
    }
}