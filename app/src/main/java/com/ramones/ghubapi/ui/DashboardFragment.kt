package com.ramones.ghubapi.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ramones.ghubapi.R
import com.ramones.ghubapi.base.BaseFragment
import com.ramones.ghubapi.databinding.FragmentDashboardBinding
import com.ramones.ghubapi.networking.helper.ApiResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override fun inflateBinding(): Class<FragmentDashboardBinding> = FragmentDashboardBinding::class.java
    override fun setContent(): Int = R.layout.fragment_dashboard

    private val viewModel: DashboardViewModel by viewModels()

    override fun setUpView() {
        super.setUpView()
    }

    override fun setUpViewBinding() {
        super.setUpViewBinding()
        viewModel.repositories.observe(viewLifecycleOwner, Observer {
            when(it){
                is ApiResponse.Failure -> {}
                is ApiResponse.Loading -> {}
                is ApiResponse.Success ->{}
            }
        })
    }

    override fun setUpViewModelBinding() {
        super.setUpViewModelBinding()
    }
}