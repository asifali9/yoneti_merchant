package com.example.yonetimerchant.dialog_fragment

import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.Countries
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ChangeAddressAdapter
import com.example.yonetimerchant.databinding.FragmentChangeAddressDialogBinding
import com.example.yonetimerchant.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeAddressDialogFragment :
    BaseDialogFragment<ProfileViewModel, FragmentChangeAddressDialogBinding>() {
    private lateinit var addressAdapter: ChangeAddressAdapter
    var listOfCountires = ArrayList<Countries>()

    val changeAddressViewModel: ProfileViewModel by viewModels({ requireParentFragment() })
//    init {
//        for (i in 1..10)
//            listOfCountires.add(Countries())
//    }

    override fun getLayout(): Int = R.layout.fragment_change_address_dialog

    override fun bindingToView() {
        dataBinding.etSearchCountry.addTextChangedListener {
            var keyword = it.toString().trim()
            if (keyword.isNotEmpty())
                changeAddressViewModel.searchCountry(keyword)
        }
        changeAddressViewModel.countryList.observe(this, Observer {
            if (it.size == 0)
                Toast.makeText(
                    requireContext(),
                    "No Record Found",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                if (!this::addressAdapter.isInitialized) {
                    listOfCountires = it
                    addressAdapter = ChangeAddressAdapter(listOfCountires, this, null)
                    dataBinding.rvCountriesDialog.adapter = addressAdapter
                }else
                {
                    listOfCountires.clear()
                    addressAdapter.notifyDataSetChanged()
                    listOfCountires.addAll(it)
                    addressAdapter.notifyItemRangeInserted(0,listOfCountires.size)
                }
            }

        })
//        for (i in 1..10)
//            listOfCountires.add(Countries())
//
    }

    fun selectCountry(countryName: String) {
        dataBinding.etSearchCountry
    }
}