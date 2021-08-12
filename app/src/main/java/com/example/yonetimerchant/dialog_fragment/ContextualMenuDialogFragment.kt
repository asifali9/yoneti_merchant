package com.example.yonetimerchant.dialog_fragment

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.example.yoneti.base.BaseDialogFragment
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.DialogFragmentContextualMenuBinding


class ContextualMenuDialogFragment :
    BaseDialogFragment<Nothing, DialogFragmentContextualMenuBinding>() {

    override fun getLayout(): Int = R.layout.dialog_fragment_contextual_menu

    override fun bindingToView() {
        dataBinding.tvCommentDialog.setOnClickListener {
            findNavController().navigate(R.id.photoCommentsDialogFragment)

        }
        dataBinding.tvLike.setOnClickListener {
            findNavController().navigate(R.id.likesDialogFragment)
        }
        dataBinding.tvShare.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "Here is the share content body"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }
}