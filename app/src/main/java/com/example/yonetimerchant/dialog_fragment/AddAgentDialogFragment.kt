package com.example.yonetimerchant.dialog_fragment

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.model.Countries
import com.example.yoneti.model.Services
import com.example.yonetimerchant.R
import com.example.yonetimerchant.adapters.ChangeAddressAdapter
import com.example.yonetimerchant.databinding.FragmentAddAgentDialogBinding
import com.example.yonetimerchant.databinding.FragmentAddServiceDialogBinding
import com.example.yonetimerchant.databinding.FragmentChangeAddressDialogBinding
import com.example.yonetimerchant.fragments.creating_service.CreateServiceViewModel
import com.example.yonetimerchant.profile.AddServiceViewModel
import com.example.yonetimerchant.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class AddAgentDialogFragment(var categoryId: String?) : BaseDialogFragment<CreateServiceViewModel, FragmentAddAgentDialogBinding>() {

    private lateinit var agentAdapter: ArrayAdapter<Services>
    val addAgentViewModel: CreateServiceViewModel by viewModels({ requireParentFragment() })
    private lateinit var serviceList: ArrayList<Services>

    override fun getLayout(): Int = R.layout.fragment_add_agent_dialog

    override fun bindingToView() {
        addAgentViewModel.getCategoryService(categoryId!!)
        setUi()

        dataBinding.btnRegisteredAgent.setOnClickListener {
            var index = dataBinding.spAgentServices.selectedItemPosition

            addAgentViewModel.addAgent(serviceList.get(index).serviceName,
                categoryId!!,
                serviceList.get(index).serviceId,
                dataBinding.etAgentFee.text.toString(),
                dataBinding.etAgentName.text.toString())
        }

    }

    private fun setUi() {
        addAgentViewModel.services.observe(this, Observer {services->
            serviceList = services
            agentAdapter = object : ArrayAdapter<Services>(requireContext(),android.R.layout.simple_spinner_dropdown_item,services) {

            }
            agentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dataBinding.spAgentServices.adapter = agentAdapter
        })
    }
}