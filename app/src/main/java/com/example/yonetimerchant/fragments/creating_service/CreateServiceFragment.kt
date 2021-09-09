package com.example.yonetimerchant.fragments.creating_service

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.yoneti.base.BaseDialogFragment
import com.example.yoneti.base.BaseFragment
import com.example.yoneti.model.Categories
import com.example.yoneti.model.MerchantServices
import com.example.yonetimerchant.R
import com.example.yonetimerchant.SpinnerCategoryItemModel
import com.example.yonetimerchant.adapters.MerchantServicesAdapter
import com.example.yonetimerchant.adapters.ServiceCategoryAdapter
import com.example.yonetimerchant.databinding.DialogSpinnerCategoryItemsBinding
import com.example.yonetimerchant.databinding.FragmentCreateServiceBinding
import com.example.yonetimerchant.dialog_fragment.AddAgentDialogFragment
import com.example.yonetimerchant.dialog_fragment.AddServiceDialogFragment
import com.example.yonetimerchant.dialog_fragment.UpdateServiceDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CreateServiceFragment : BaseFragment<CreateServiceViewModel, FragmentCreateServiceBinding>() {
    private var updatedItemPosition: Int = -1
    private lateinit var updateServicedialog: UpdateServiceDialogFragment
    private lateinit var addServiceDialog: AddServiceDialogFragment
    private lateinit var servicesList: java.util.ArrayList<MerchantServices>
    private lateinit var merchantServiceAdapter: MerchantServicesAdapter
    private lateinit var addAgentDialog: AddAgentDialogFragment
    private var selectedCategory: Categories? = null
    private lateinit var serviceCategories: ArrayList<Categories>
    lateinit var categoryDialog: SpinnerCategoryItemsDialog
    var TAG = CreateServiceFragment::class.java.simpleName
    override fun getViewMode(): Class<CreateServiceViewModel> = CreateServiceViewModel::class.java

    override fun getLayout(): Int {
        return R.layout.fragment_create_service
    }

    override fun bindingToViews() {
//        binding.serviceCategories.setItems(serviceCategories)
        viewModel!!.getServiceCategories()


        setUi()
        clickListeners()
        viewModel!!.serviceCateogryList.observe(this, Observer { categories ->
            serviceCategories = categories
            if (categories.size == 1) {
                selectedCategory = serviceCategories.get(0)
                binding.tvServiceCategory.text = categories.get(0).categoryName
                binding.tvServiceCategory.isClickable = false
                viewModel!!.getServicesAgainstId(categories.get(0).categoryId!!)
            } else if (categories.size > 1)
                categoryDialog = SpinnerCategoryItemsDialog(categories, ::onSelectedCategory)
        })

        binding.tvServiceCategory.setOnClickListener {
            if (serviceCategories.size > 1) {
                categoryDialog = SpinnerCategoryItemsDialog(serviceCategories, ::onSelectedCategory)
                categoryDialog.show(childFragmentManager, "dialog")
            }
        }
    }

    private fun setUi() {

        binding.btnAddAgent.isClickable = binding.switch1.isChecked
        binding.switch1.setOnCheckedChangeListener { compoundButton, isOn ->
            binding.btnAddAgent.isClickable = isOn
        }

        viewModel!!.merchantServices.observe(this, Observer {
            if (it.size > 0) {
                binding.merchantServices.visibility = View.VISIBLE
                servicesList = it
                merchantServiceAdapter = MerchantServicesAdapter(it, CreateServiceFragment@ this)
                binding.merchantServices.adapter = merchantServiceAdapter
            }
        })

        viewModel!!.serviceAddedSuccessful.observe(this, Observer {
            if (it && viewModel!!.newlyCreatedService != null) {
                if (!this::servicesList.isInitialized)
                    servicesList = ArrayList()
                servicesList.add(viewModel!!.newlyCreatedService!!)
                if (this::merchantServiceAdapter.isInitialized)
                merchantServiceAdapter.notifyItemInserted(servicesList.size - 1)
                else
                {
                    merchantServiceAdapter = MerchantServicesAdapter(servicesList,this)
                    binding.merchantServices.visibility = View.VISIBLE
                    binding.merchantServices.adapter = merchantServiceAdapter
                }
                addServiceDialog.dismiss()
            }
        })
    }

    private fun clickListeners() {
        /**
         * add services
         */
        binding.btnAddService.setOnClickListener {
            if (selectedCategory != null) {
                addServiceDialog = AddServiceDialogFragment(selectedCategory!!.categoryId)
                addServiceDialog.show(childFragmentManager, "addService")
            }
        }

        binding.btnAddAgent.setOnClickListener {
            if (selectedCategory != null) {
                addAgentDialog = AddAgentDialogFragment(selectedCategory!!.categoryId)
                addAgentDialog.show(childFragmentManager, "agentDialog")
            } else
                Toast.makeText(requireContext(), "select Category First", Toast.LENGTH_SHORT).show()
        }

        viewModel!!.isServiceDeleted.observe(this, Observer { isServiceDeleted ->
            if (isServiceDeleted) {
                servicesList.removeAt(updatedItemPosition)
                merchantServiceAdapter.notifyItemRemoved(updatedItemPosition)
                updatedItemPosition = -1
            }
        })
    }


    fun onSelectedCategory(index: Int = -1) {
        categoryDialog.dismiss()
        selectedCategory = serviceCategories.get(index)
        Toast.makeText(
            requireContext(),
            "${serviceCategories.get(index).categoryName}",
            Toast.LENGTH_SHORT
        ).show()
        binding.tvServiceCategory.text = serviceCategories.get(index).categoryName
    }

    /**
     * update Service
     */
    fun updateService(obj: MerchantServices, position: Int) {
        updatedItemPosition = position
        updateServicedialog = UpdateServiceDialogFragment(obj, ::updateServiceObject)
        updateServicedialog.show(childFragmentManager, "show")
    }

    /**
     * updating the service and resetting the index to its original position
     */
    fun updateServiceObject(service: MerchantServices? = null) {
        Toast.makeText(
            requireContext(),
            "updating at index $updatedItemPosition",
            Toast.LENGTH_SHORT
        ).show()
        if (service != null) {
            servicesList.set(updatedItemPosition, service)
            merchantServiceAdapter.notifyItemChanged(updatedItemPosition)
            updatedItemPosition = -1
        }
    }

    /**
     * Service Category Dialog fragment
     */
    class SpinnerCategoryItemsDialog constructor(
        var categories: java.util.ArrayList<Categories>,
        var kFunction1: (Int) -> Unit
    ) :
        BaseDialogFragment<SpinnerCategoryItemModel, DialogSpinnerCategoryItemsBinding>() {

        override fun getLayout(): Int = R.layout.dialog_spinner_category_items

        override fun bindingToView() {
            dataBinding.rvCategory.adapter = ServiceCategoryAdapter(categories, this)


        }

        fun selectedCategory(position: Int) {
            kFunction1(position)
        }
    }
}