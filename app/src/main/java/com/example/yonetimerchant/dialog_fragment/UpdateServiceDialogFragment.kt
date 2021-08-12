package com.example.yonetimerchant.dialog_fragment

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.MerchantServices
import com.example.yoneti.model.Services
import com.example.yonetimerchant.R
import com.example.yonetimerchant.databinding.FragmentEditServiceDialogBinding
import com.example.yonetimerchant.fragments.creating_service.CreateServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class UpdateServiceDialogFragment(
    var updateService: MerchantServices,
    var updateObject: (MerchantServices?) -> Unit
) : BaseDialogFragment<CreateServiceViewModel, FragmentEditServiceDialogBinding>() {

    private lateinit var serviceList: ArrayList<Services>
    private lateinit var adapter: ArrayAdapter<Services>
    val updateServiceViewModel: CreateServiceViewModel by viewModels({ requireParentFragment() })

    override fun getLayout(): Int = R.layout.fragment_edit_service_dialog

    override fun bindingToView() {
        setUi()
        clickListeners()
//
//        dataBinding.btnCreateService.setOnClickListener {
//            var index = dataBinding.etServiceTitle.selectedItemPosition
//
//            addAddressViewModel.registeredNewService(serviceList.get(index).serviceName,
//                categoryId!!,
//                serviceList.get(index).serviceId,
//                dataBinding.etServiceCharges.text.toString(),
//                dataBinding.etEstimatedTime.text.toString())
//        }
    }

    private fun clickListeners() {
        dataBinding.btnEditService.setOnClickListener {
            if (!dataBinding.etEstimatedTime.text.toString().equals(updateService.estimatedTime) || !dataBinding.etServiceCharges.text.toString().equals(updateService.serviceCharged))
            {
                updateServiceViewModel.updateService(updateService.serviceId,updateService.serviceCharged,updateService.estimatedTime)
                updateService.serviceCharged = dataBinding.etServiceCharges.text.toString()
                updateService.estimatedTime = dataBinding.etEstimatedTime.text.toString()
            }
            else
                Toast.makeText(requireContext(), "no change found", Toast.LENGTH_SHORT).show()
        }

        dataBinding.btnDeleteService.setOnClickListener {
            updateServiceViewModel.deleteService(updateService.serviceId)
        }
    }

    private fun setUi() {
        dataBinding.etServiceTitle.setText(updateService.serviceTitle)
        dataBinding.etEstimatedTime.setText(updateService.estimatedTime)
        dataBinding.etServiceCharges.setText(updateService.serviceCharged)


        updateServiceViewModel.isServiceDeleted.observe(this, Observer {
            if (it)
                dismiss()
        })

        updateServiceViewModel.isServiceUpdated.observe(this, Observer {
            Log.d("TAG", "setUi: ${updateService.estimatedTime}")
            if (it)
            {
                updateObject(updateService)
                dismiss()
            }
        })

    }
}