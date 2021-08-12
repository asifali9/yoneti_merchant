package com.example.yonetimerchant.dialog_fragment

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.MerchantServices
import com.example.yoneti.model.Services
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentAddServiceDialogBinding
import com.example.yonetimerchant.fragments.creating_service.CreateServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class AddServiceDialogFragment(var categoryId: String?) : BaseDialogFragment<CreateServiceViewModel, FragmentAddServiceDialogBinding>() {

    private lateinit var serviceList: ArrayList<Services>
    private lateinit var adapter: ArrayAdapter<Services>
    val addAddressViewModel: CreateServiceViewModel by viewModels({ requireParentFragment() })

    override fun getLayout(): Int = R.layout.fragment_add_service_dialog

    override fun bindingToView() {
        addAddressViewModel.getCategoryService(categoryId!!)
        setUi()

        dataBinding.btnCreateService.setOnClickListener {
            var index = dataBinding.etServiceTitle.selectedItemPosition

            addAddressViewModel.registeredNewService(serviceList.get(index).serviceName,
                categoryId!!,
                serviceList.get(index).serviceId,
                dataBinding.etServiceCharges.text.toString(),
                dataBinding.etEstimatedTime.text.toString())
        }
    }

    private fun setUi() {
        addAddressViewModel.services.observe(this, Observer {services->
            serviceList = services
            Toast.makeText(requireContext(), "${services.size}", Toast.LENGTH_SHORT).show()
            adapter = object : ArrayAdapter<Services>(requireContext(),android.R.layout.simple_spinner_dropdown_item,services) {

            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dataBinding.etServiceTitle.adapter = adapter
        })
    }
}